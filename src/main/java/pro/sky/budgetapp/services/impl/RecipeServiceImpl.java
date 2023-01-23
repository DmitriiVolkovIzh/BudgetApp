package pro.sky.budgetapp.services.impl;


import org.springframework.stereotype.Service;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.services.RecipeService;

import java.util.Map;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {

    private Long recipeId = 1L;
    private final Map<Long, Recipe> listRecipes = new TreeMap<>();

    @Override
    public Recipe getRecipe(Long recipeId) {
        return listRecipes.get(recipeId);
    }


    @Override
    public Long addRecipe(Recipe recipe) {
        listRecipes.put(recipeId, recipe);
        return recipeId++;
    }

    @Override
    public Recipe editRecipe(Long recipeId, Recipe recipe) {
        if (listRecipes.containsKey(recipeId)) {
            listRecipes.put(recipeId, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long recipeId) {
        if (listRecipes.containsKey(recipeId)) {
            listRecipes.remove(recipeId);
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return listRecipes;
    }
}