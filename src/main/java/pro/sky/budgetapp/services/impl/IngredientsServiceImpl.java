package pro.sky.budgetapp.services.impl;

import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.services.IngredientsService;

import java.util.Map;
import java.util.TreeMap;

public class IngredientsServiceImpl implements IngredientsService {

    private Long ingredientId = 1L;
    private final Map<Long, Ingredient> listIngredients = new TreeMap<>();

    @Override
    public Ingredient getIngredients(Long ingredientId) {
        for (Map.Entry<Long, Ingredient> entry : listIngredients.entrySet()) {
            if (entry != null && entry.getKey() == ingredientId) {
                Ingredient ingredients = listIngredients.get(ingredientId);
                if (ingredients != null) {
                    return ingredients;
                }
            }
        }
        return null;
    }
    @Override
    public Ingredient editIngredients(Long ingredientId, Ingredient ingredients) {
        if (listIngredients.containsKey(ingredientId)) {
            listIngredients.put(ingredientId, ingredients);
            return ingredients;
        }
        return null;
    }
    @Override
    public long addIngredients(Ingredient ingredients) {
        listIngredients.getOrDefault(ingredientId, null);
        listIngredients.put(ingredientId, ingredients);
        return ingredientId++;
    }
    @Override
    public boolean deleteIngredient(Long ingredientId) {
        if (listIngredients.containsKey(ingredientId)) {
            listIngredients.remove(ingredientId);
            return true;
        }
        return false;
    }
    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return listIngredients;
    }


}
