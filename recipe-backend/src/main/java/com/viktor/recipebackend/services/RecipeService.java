package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.other.user_with_recipes.UserWithHisRecipes;
import com.viktor.recipebackend.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeService(RecipeRepository recipeRepository,
                         UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public Optional<Recipe> getRecipeById(UUID idRecipe) {
        return recipeRepository.existsById(idRecipe) ? recipeRepository.findById(idRecipe) : Optional.empty();
    }
    public Recipe findRecipeById(UUID idRecipe) {
        Recipe recipe = recipeRepository.findRecipeById(idRecipe);
        return recipe;
    }

    public UserWithHisRecipes getRecipesByUserId(UUID idUser) {
        Optional<User> user = userService.getUserById(idUser);
        List<Recipe> recipes = recipeRepository.getRecipesByIdUser(idUser);
        return new UserWithHisRecipes(user.orElse(null), recipes);
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