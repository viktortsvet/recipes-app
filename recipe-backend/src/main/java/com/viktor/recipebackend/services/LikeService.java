package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Like;
import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.LikesRepository;
import com.viktor.recipebackend.structures.LikeDto;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikesRepository likesRepository;
    private final UserService userService;
    private final RecipeService recipeService;
    private final QueryService queryService;

    public void saveLike(@Nonnull LikeDto likeDto) throws EntityNotFoundException {
        UUID idUser = likeDto.getIdUser();
        UUID idRecipe = likeDto.getIdRecipe();
        String boolSql = "select exists(select id from likes where id_user = :idUser " +
                "and id_recipe = :idRecipe)";
        Map<String, Object> params = new HashMap<>();
        params.put("idUser", idUser);
        params.put("idRecipe", idRecipe);
        boolean bool = queryService.executeSqlSingleBooleanResult(boolSql, params);
        if (bool) {
            UUID existingIdLike = likesRepository.findByIdUserAndIdRecipe(idUser, idRecipe).get(0);
            likesRepository.deleteById(existingIdLike);
        } else {
            User user = userService.getUserById(idUser).orElseThrow();
            Recipe recipe = recipeService.findRecipeById(idRecipe).orElseThrow();
            if (user.getId() == null) {
                throw new EntityNotFoundException("User wasn't found.");
            }
            if (recipe.getId() == null) {
                throw new EntityNotFoundException("Recipe wasn't found.");
            }
            Like like = new Like();
            like.setRecipe(recipe);
            like.setUser(user);
            likesRepository.save(like);
        }
    }

    public long getRecipeLikesQuantity(@Nonnull UUID idRecipe) {
        final String sql = "select count(*) from likes where id_recipe = :idRecipe";
        Map<String, Object> params = new HashMap<>();
        params.put("idRecipe", idRecipe);
        return queryService.executeSqlSingleLongResult(sql, params);
    }

    public List<Like> findLikesByIds(@Nonnull List<UUID> ids) {
        return likesRepository.findAllById(ids);
    }

    public void removeLike(@Nonnull UUID id) {
        if (likesRepository.existsById(id)) {
            likesRepository.deleteById(id);
        }
    }
}
