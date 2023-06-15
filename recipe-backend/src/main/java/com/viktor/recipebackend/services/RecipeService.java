package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.RecipeRepository;
import com.viktor.recipebackend.structures.QueryResultData;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final QueryService queryService;

    public RecipeService(RecipeRepository recipeRepository,
                         UserService userService,
                         QueryService queryService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.queryService = queryService;
    }

    public Optional<Recipe> getRecipeById(UUID idRecipe) {
        return recipeRepository.existsById(idRecipe) ? recipeRepository.findById(idRecipe) : Optional.empty();
    }
    public Optional<Recipe> findRecipeById(UUID idRecipe) {
        return recipeRepository.findById(idRecipe);
    }

    public List<Recipe> getRecipesByName(String names) {
        Map<String, Object> params = new HashMap<>();
        params.put("names", new ArrayList<>(Arrays.asList(names.split(", "))));
        String sql = "select r.* from recipes r where r.recipe_name in :names";
        return queryService.executeSql(sql, Recipe.class, params, null, null, null);
    }

    public QueryResultData<List<Map<String, Object>>> getRecipesByUserId(Integer start, Integer pageSize, UUID idUser) {
        String sql = "select cast(r.id as text), r.recipe_name, r.description from recipes r " +
                "where r.id_user = :idUser";
        String sqlCount = "select count(*) from recipes where id_user = :idUser";
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("idUser", idUser);
        long recordsLength = queryService.executeSqlSingleLongResult(sqlCount, countParams);
        Map<String, Object> params = new HashMap<>();
        params.put("idUser", idUser);
        List<Object> objectItems = queryService.executeSql(sql, null, params, QueryService.Bounds.BOUND_BOTH, start, pageSize);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object objectItem : objectItems) {
            Map<String, Object> map = new HashMap<>();
            Object[] objectArr = ((Object[])objectItem);
            UUID id = UUID.fromString(objectArr[0].toString());
            String recipeName = objectArr[1] != null ? objectArr[1].toString() : null;
            String description = objectArr[2] != null ? objectArr[2].toString() : null;
            map.put("id", id);
            map.put("recipeName", recipeName);
            map.put("description", description);
            resultList.add(map);
        }
        return new QueryResultData<>(resultList, recordsLength);
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