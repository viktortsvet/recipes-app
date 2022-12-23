package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.services.ExportDocumentService;
import com.viktor.recipebackend.services.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("exportDoc")
public class ExportDocumentController {
    private final ExportDocumentService exportDocumentService;
    private final RecipeService recipeService;

    @Autowired
    public ExportDocumentController(ExportDocumentService exportDocumentService,
                                    RecipeService recipeService) {
        this.exportDocumentService = exportDocumentService;
        this.recipeService = recipeService;
    }

    @PostMapping(value = "recipe-word")
    public ResponseEntity<?> getExportWordRecipe(@RequestParam(value = "idRecipe") UUID idRecipe,
                                                 HttpServletResponse response) {
        Recipe recipe = recipeService.findRecipeById(idRecipe);
        if (recipe != null) {
            exportDocumentService.exportRecipeWord(recipe, response);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}