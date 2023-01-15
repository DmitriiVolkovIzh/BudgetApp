package pro.sky.budgetapp.services.impl;

import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.services.IngredientsService;
import pro.sky.budgetapp.services.RecipeService;

public class IngredientsServiceImpl implements IngredientsService {

    private RecipeService recipeService;

    @Override
    public void add(int id, String name, int count, String format) {
        recipeService.find(id).getIngredients().add(new Ingredient(name,count,format));
    }

    @Override
    public Ingredient get(int id, String name) {
        return recipeService.find(id).getIngredients().stream().filter(ingredient -> ingredient.getName().equals(name)).findAny().orElse(null);
    }
}
