package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.other.RecipeDTO;
import com.viktor.recipebackend.other.user_with_recipes.UserWithHisRecipes;
import com.viktor.recipebackend.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("recipesDto")
    public ResponseEntity<List<RecipeDTO>> getRecipeDto() {
        return new ResponseEntity<>(recipeService.getRecipeDto(), HttpStatus.OK);
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