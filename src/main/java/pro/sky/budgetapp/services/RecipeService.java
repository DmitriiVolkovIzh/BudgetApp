package pro.sky.budgetapp.services;

import pro.sky.budgetapp.model.recipes.Recipe;

import java.util.Map;

public interface RecipeService {
    Recipe getRecipe(Long recipeId);

    Long addRecipe(Recipe recipe);

    Recipe editRecipe(Long recipeId, Recipe recipe);

    boolean deleteRecipe(Long recipeId);

    Map<Long, Recipe> getAllRecipes();
}
