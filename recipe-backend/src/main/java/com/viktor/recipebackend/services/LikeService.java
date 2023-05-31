package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Like;
import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.LikesRepository;
import com.viktor.recipebackend.structures.LikeDto;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikesRepository likesRepository;
    private final UserService userService;
    private final RecipeService recipeService;

    public void saveLike(@Nonnull LikeDto likeDto) {
        Like like;
        User user = userService.getUserById(likeDto.getIdUser()).orElseThrow();
        Recipe recipe = recipeService.findRecipeById(likeDto.getIdRecipe());
        if (likeDto.getId() != null && likesRepository.existsById(likeDto.getId())) {
            like = new Like(likeDto.getId(), user, recipe);
        } else {
            like = new Like();
            like.setUser(user);
            like.setRecipe(recipe);
        }
        likesRepository.save(like);
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
