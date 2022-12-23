package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ExportDocumentService {
    private final FileIOService fileIOService;
    @Autowired
    public ExportDocumentService(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    private XWPFDocument createWordDocument() {
        return new XWPFDocument();
    }

    private XWPFParagraph createDocumentWordParagraph(XWPFDocument document,
                                                      String content,
                                                      String fontFamily,
                                                      int fontSize,
                                                      ParagraphAlignment paragraphAlignment) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        paragraph.setAlignment(paragraphAlignment);
        return paragraph;
    }

    public void exportRecipeWord(Recipe recipe, HttpServletResponse response) {
        XWPFDocument document = createWordDocument();
        createDocumentWordParagraph(document, recipe.getRecipeName(), "Times New Roman", 16, ParagraphAlignment.CENTER);
        createDocumentWordParagraph(document, recipe.getDescription(), "Times New Roman", 14, ParagraphAlignment.LEFT);
        try {
            File fileOut = fileIOService.writeFile(document, "RecipeWord", ".doc");
            fileIOService.generateFile(fileOut, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}