package pro.sky.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.services.RecipeService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import javax.validation.Valid;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "СRUD - Операции и другие эдпоинты для работы")
public class RecipeController {
    private final RecipeService recipeService;


    @PostMapping()
    public ResponseEntity<Long> addRecipe(@Valid @RequestBody Recipe recipe) {
        Long createdRecipe = recipeService.addRecipe(recipe);
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
    @Operation(
            summary = "Поиск рецептов"
    )
    @Parameters(value = {
            @Parameter(name = "Recipe", example = "Макароны по-флотски")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    }
    )
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
    @Operation(
            summary = "Удаление рецепта",
            description = "Удалить можно по id номеру"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    }
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/createTextFile")
    public ResponseEntity<Object> createTempTextFileWithRecipes(@RequestParam String fileName){
        try {
            if (recipeService.getTextFile(fileName) == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = recipeService.getTextFile(fileName);
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipesText.txt\"")
                    .contentLength(Files.size(path))
                    .body(resource);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}




