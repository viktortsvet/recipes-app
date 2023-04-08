package com.viktor.recipebackend.other;

import com.viktor.recipebackend.entities.User;

public class RecipeDTO {
    private User user;
    private String recipeName;
    private String description;

    public RecipeDTO() {
    }

    public RecipeDTO(User user, String recipeName, String description) {
        this.user = user;
        this.recipeName = recipeName;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}