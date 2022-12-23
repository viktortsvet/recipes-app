package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    @Query("select r from Recipe as r where r.id =:idRecipe")
    Recipe findRecipeById(@Param("idRecipe") UUID idRecipe);
}