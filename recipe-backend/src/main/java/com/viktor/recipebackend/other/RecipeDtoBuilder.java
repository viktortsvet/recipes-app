package com.viktor.recipebackend.other;

import com.viktor.recipebackend.entities.User;
import org.springframework.lang.NonNull;

public class RecipeDtoBuilder {
    private User user;
    private String recipeName;
    private String description;

    public RecipeDtoBuilder() {}

    public RecipeDtoBuilder setUser(@NonNull User user) {
        this.user = user;
        return this;
    }

    public RecipeDtoBuilder setDescription(@NonNull String description) {
        this.description = description;
        return this;
    }

    public RecipeDtoBuilder setRecipeName(@NonNull String recipeName) {
        this.recipeName = recipeName;
        return this;
    }

    public RecipeDTO build() {
        return new RecipeDTO(user, recipeName, description);
    }
}