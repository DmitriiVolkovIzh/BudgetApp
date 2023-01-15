package pro.sky.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.services.impl.IngredientsServiceImpl;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private IngredientsServiceImpl ingredientsService;

    @GetMapping("/addnew")
    public void addIngredient(@RequestParam int id, @RequestParam String name, @RequestParam int count, @RequestParam String format){
        ingredientsService.add(id,name,count,format);
    }
    @GetMapping("/getingredient")
    public Ingredient getIngredient(@RequestParam int id, @RequestParam String name){
        return ingredientsService.get(id,name);
    }
}