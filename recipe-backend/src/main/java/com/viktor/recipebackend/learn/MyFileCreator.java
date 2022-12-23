package com.viktor.recipebackend.learn;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileCreator {
    private static void testFile() throws IOException, InvalidFormatException {
        XWPFDocument document = new XWPFDocument();
        InputStream inputStream = Files.newInputStream(Path.of("E:\\моя старая фотка.jpg"));
        Path path = Files.createFile(Path.of("E:\\newTestWord.doc"));
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addPicture(inputStream, Document.PICTURE_TYPE_JPEG, "E:\\моя старая фотка.jpg", Units.toEMU(150), Units.toEMU(150));
        inputStream.close();
        OutputStream outputStream = Files.newOutputStream(path);
        document.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        testFile();
    }
}