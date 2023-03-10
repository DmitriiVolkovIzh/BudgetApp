package pro.sky.budgetapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.services.IngredientsService;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/ingredients")

public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping()
    public ResponseEntity<Long> addIngredients( @Valid @RequestBody Ingredient ingredients) {
        long id = ingredientsService.addIngredients(ingredients);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredients( @PathVariable long id) {
        Ingredient ingredients = ingredientsService.getIngredients(id);
        if (ingredients != null) {
            return ResponseEntity.ok(ingredients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredients( @PathVariable long id,
                                                      @RequestBody Ingredient ingredients) {
        Ingredient ingredient = ingredientsService.editIngredients(id, ingredients);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        if (ingredientsService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping()
    public ResponseEntity<Map<Long, Ingredient>> getAllIngredients() {
        Map<Long, Ingredient> ingredientsList = ingredientsService.getAllIngredients();
        if (ingredientsList != null) {
            return ResponseEntity.ok(ingredientsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}