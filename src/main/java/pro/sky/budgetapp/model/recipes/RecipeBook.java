package pro.sky.budgetapp.model.recipes;

import java.util.*;

public class RecipeBook {

    private Map<Integer, Recipe> recipeBook;

    public RecipeBook(){
        this.recipeBook=new HashMap<>();

        recipeBook.put(1, new Recipe("Яичница", 10, new ArrayList<>(List.of(new Ingredient("Яйца", 3, "шт"),
                new Ingredient("Варенная колбаса", 100, "гр"), new Ingredient("Специи", 0, "по вкусу"),
                new Ingredient("Масло растительное", 2, "Чайных ложки"))), new LinkedList<>(List.of("Выливаем масло на сковороду", "Разогреваем сковородку","Жарим яйца", "Добавляем колбасу", "Добавляем соль и специи по вкусу","Готовим до готовности" ))));
    }

    public void addRecipe(int id, String title, int time, ArrayList<Ingredient> ingredients, String...steps){

        recipeBook.put(id, new Recipe(title, time, ingredients, new LinkedList<>(List.of(steps))));

    }

    public Recipe getRecipe(int id){
        return recipeBook.get(id);
    }


}