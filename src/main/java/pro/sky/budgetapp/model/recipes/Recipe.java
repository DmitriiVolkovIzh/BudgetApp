package pro.sky.budgetapp.model.recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Recipe {
    @NotBlank(message = "Укажите обязательно название рецепта")
    private String title;
    @Positive
    private int time;
    @NotEmpty
    private List<Ingredient> ingredientsList;
    private List<String> instruction;
}