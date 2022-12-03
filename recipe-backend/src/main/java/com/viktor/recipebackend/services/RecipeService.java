package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> getRecipeById(UUID idRecipe) {
        return recipeRepository.existsById(idRecipe) ? recipeRepository.findById(idRecipe) : Optional.empty();
    }

    public void addOrUpdateRecipe(Recipe recipe) {
        if (recipe.getId() != null && recipeRepository.existsById(recipe.getId())) {
            recipeRepository.save(recipe);
        } else {
            Recipe newRecipe = new Recipe();
            newRecipe.setRecipeName(recipe.getRecipeName());
            newRecipe.setDescription(recipe.getDescription());
            newRecipe.setUser(recipe.getUser());
            recipeRepository.save(newRecipe);
        }
    }
}