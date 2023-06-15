package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LikesRepository extends JpaRepository<Like, UUID> {
    @Query("select l.id from Like l where l.user.id = :idUser and l.recipe.id = :idRecipe")
    List<UUID> findByIdUserAndIdRecipe(@Param("idUser") UUID idUser, @Param("idRecipe") UUID idRecipe);
}
