package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.services.RecipeService;
import com.viktor.recipebackend.structures.QueryResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipes-by-userId")
    public ResponseEntity<QueryResultData<List<Map<String, Object>>>> getRecipesByUserId(
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "idUser") UUID idUser) {
        return new ResponseEntity<>(recipeService.getRecipesByUserId(start, pageSize, idUser), HttpStatus.OK);
    }

    @GetMapping("getRecipesByName")
    public ResponseEntity<List<Recipe>> getRecipesByName(@RequestParam(value = "names") String names) {
        return new ResponseEntity<>(recipeService.getRecipesByName(names), HttpStatus.OK);
    }

    @PostMapping("createRecipe")
    public ResponseEntity<?> createRecipe(@RequestParam(value = "idUser") UUID idUser,
                                          @RequestParam(value = "recipeName") String recipeName,
                                          @RequestParam(value = "description") String description) {
        recipeService.createRecipe(idUser, recipeName, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("addOrUpdate")
    public ResponseEntity<?> addOrUpdateRecipe(@RequestBody Recipe recipe) {
        if (recipe != null) {
            recipeService.addOrUpdateRecipe(recipe);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}