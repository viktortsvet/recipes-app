package com.viktor.recipebackend.other.user_with_recipes;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;

import java.util.List;

public class UserWithHisRecipes {
    private User user;
    private List<Recipe> recipes;

    public UserWithHisRecipes() {}

    public UserWithHisRecipes(User user, List<Recipe> recipes) {
        this.user = user;
        this.recipes = recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public User getUser() {
        return user;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setUser(User user) {
        this.user = user;
    }
}