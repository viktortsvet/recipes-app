package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.services.ExportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("exportDoc")
public class ExportDocumentController {
    private final ExportDocumentService exportDocumentService;

    @Autowired
    public ExportDocumentController(ExportDocumentService exportDocumentService) {
        this.exportDocumentService = exportDocumentService;
    }

    @GetMapping("recipe-word")
    public ResponseEntity<?> getExportWordRecipe(@RequestParam(value = "recipe")Recipe recipe,
                                                 HttpServletResponse response) {
        try {
            exportDocumentService.exportRecipeWord(recipe, response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}