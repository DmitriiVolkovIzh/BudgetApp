package pro.sky.budgetapp.services;

import pro.sky.budgetapp.model.recipes.Ingredient;

public interface IngredientsService {
    void add(int id, String name, int count, String format);

    Ingredient get(int id, String name);
}
