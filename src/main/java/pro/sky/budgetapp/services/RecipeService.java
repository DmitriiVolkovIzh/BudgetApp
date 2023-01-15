package pro.sky.budgetapp.services;

import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.model.recipes.Recipe;

import java.util.ArrayList;

public interface RecipeService {
    void add(int id, String title, int time, ArrayList<Ingredient> ingredients, String... steps);

    Recipe find(int id);
}
