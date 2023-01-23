package pro.sky.budgetapp.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.services.impl.RecipeServiceImpl;

import java.util.ArrayList;

@RestController
@RequestMapping("/recipe")

public class RecipeController {
    private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public void addRecipe(@RequestParam int id, @RequestParam String title, @RequestParam int time,
                          @RequestParam ArrayList<Ingredient> ingredients, @RequestParam String...steps){

        recipeService.add(id,title,time,ingredients, steps);

    }
    @GetMapping("/get")
    public Recipe getRecipeById(@RequestParam int id){
        return recipeService.find(id);
    }

}

