package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.other.RecipeDTO;
import com.viktor.recipebackend.other.RecipeDtoBuilder;
import com.viktor.recipebackend.other.user_with_recipes.UserWithHisRecipes;
import com.viktor.recipebackend.repositories.RecipeRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final QueryService queryService;
    private static final String sqlRecipeDto = "select r.recipe_name as recipe_name, " +
            "r.description as description, cast(r.id_user as text) as id_user from recipes r\n" +
            "left join users u on r.id_user = u.id";

    public RecipeService(RecipeRepository recipeRepository,
                         UserService userService,
                         QueryService queryService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.queryService = queryService;
    }

    public List<RecipeDTO> getRecipeDto() {
        List<Object> recipes = queryService.executeSql(sqlRecipeDto);
        List<RecipeDTO> result = new ArrayList<>();
        RecipeDtoBuilder recipeDtoBuilder = new RecipeDtoBuilder();
        for (Object item : recipes) {
            Object[] itemArr = ((Object[])item);
            if (itemArr[0] != null) {
                recipeDtoBuilder.setRecipeName(itemArr[0].toString());
            }
            if (itemArr[1] != null) {
                recipeDtoBuilder.setDescription(itemArr[1].toString());
            }
            if (itemArr[2] != null) {
                User user = userService.getUserById(UUID.fromString(itemArr[2].toString())).orElseThrow();
                recipeDtoBuilder.setUser(user);
            }
            result.add(recipeDtoBuilder.build());
        }
        return result;
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

    public void createRecipe(@NonNull UUID idUser, @NonNull String recipeName, @NonNull String description) {
        User user = userService.getUserById(idUser).orElseThrow();
        Recipe recipe = new Recipe();
        recipe.setUser(user);
        recipe.setRecipeName(recipeName);
        recipe.setDescription(description);
        recipeRepository.save(recipe);
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