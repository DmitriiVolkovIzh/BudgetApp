package pro.sky.budgetapp.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.model.recipes.RecipeBook;
import pro.sky.budgetapp.services.RecipeService;

import java.util.ArrayList;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeBook recipeBook;

    @Override
    public Recipe find(int id) {
        return recipeBook.getRecipe(id);
    }


    @Override
    public void add(int id, String title, int time, ArrayList<Ingredient> ingredients, String[] steps) {
        recipeBook.addRecipe(id,title,time,ingredients, steps);
    }
}