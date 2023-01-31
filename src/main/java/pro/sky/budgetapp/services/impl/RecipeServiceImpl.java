package pro.sky.budgetapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.budgetapp.model.recipes.Recipe;
import pro.sky.budgetapp.services.FileService;
import pro.sky.budgetapp.services.RecipeService;
import com.fasterxml.jackson.core.type.TypeReference;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {


    private Map<Long, Recipe> listRecipes = new TreeMap<>();

    private Long recipeId = 1L;

    final private FileService fileService;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public Recipe getRecipe(Long recipeId) {
        return listRecipes.get(recipeId);
    }

    @Override
    public Long addRecipe(Recipe recipe) {
        listRecipes.put(recipeId, recipe);
        saveToFile();
        return recipeId++;
    }


    @Override
    public Recipe editRecipe(Long recipeId, Recipe recipe) {
        if (listRecipes.containsKey(recipeId)) {
            listRecipes.put(recipeId, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long recipeId) {
        if (listRecipes.containsKey(recipeId)) {
            listRecipes.remove(recipeId);
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return listRecipes;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(listRecipes);
            fileService.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFileRecipes();
        try {
            listRecipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
