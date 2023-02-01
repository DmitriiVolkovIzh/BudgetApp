package pro.sky.budgetapp.services;

import java.io.File;

public interface FileService {
    boolean saveRecipeToFile(String json);

    String readFromFileRecipes();

    String readFromFileIngredients();

    boolean saveIngredientToFile(String json);

    boolean cleanFileRecipe();

    boolean cleanFileIngredient();

    File getRecipesFile();

    File getIngredientsFile();
}
