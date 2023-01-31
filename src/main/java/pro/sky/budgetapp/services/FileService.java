package pro.sky.budgetapp.services;

public interface FileService {
    boolean saveRecipeToFile(String json);

    String readFromFileRecipes();

    String readFromFileIngredients();

    boolean saveIngredientToFile(String json);
}
