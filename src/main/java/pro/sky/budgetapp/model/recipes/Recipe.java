package pro.sky.budgetapp.model.recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@Data
@AllArgsConstructor
public class Recipe {

    private String title;
    private int time;
    private List<Ingredient> ingredientsList;
    private List<String> instruction;
}