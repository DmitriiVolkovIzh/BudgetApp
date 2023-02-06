package pro.sky.budgetapp.model.recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Ingredient {
    @NotBlank(message = "Укажите обязательно название ингридиента")
    private String name;
    @Positive
    private Integer count;
    private String measure;
    @Override
    public String toString() {
        return  name + " — " + count +" " + measure;
    }
}