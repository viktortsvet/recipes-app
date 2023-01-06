package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.other.user_with_recipes.UserWithHisRecipes;
import com.viktor.recipebackend.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserWithHisRecipes> getRecipesByUserId(@RequestParam(value = "idUser") UUID idUser) {
        return new ResponseEntity<>(recipeService.getRecipesByUserId(idUser), HttpStatus.OK);
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