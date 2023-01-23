package pro.sky.budgetapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.services.RecipeService;
import pro.sky.budgetapp.services.impl.RecipeServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/recipe")

public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping()
    public ResponseEntity<Recipe> addRecipe (@RequestBody Recipe recipe){
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @GetMapping()
    public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
        Map<Long, Recipe> recipeList = recipeService.getAllRecipes();
        if (recipeList != null) {
            return ResponseEntity.ok(recipeList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}




