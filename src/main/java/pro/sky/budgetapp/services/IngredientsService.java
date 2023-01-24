package pro.sky.budgetapp.services;

import pro.sky.budgetapp.model.recipes.Ingredient;

import java.util.Map;

public interface IngredientsService {


    Ingredient getIngredients(Long ingredientId);

    Ingredient editIngredients(Long ingredientId, Ingredient ingredients);

    long addIngredients(Ingredient ingredients);

    boolean deleteIngredient(Long ingredientId);

    Map<Long, Ingredient> getAllIngredients();
}
