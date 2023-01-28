package pro.sky.budgetapp.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.Tree;
import org.springframework.stereotype.Service;
import pro.sky.budgetapp.model.recipes.Ingredient;
import pro.sky.budgetapp.services.IngredientsService;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    public static Long ingredientId = 1L;
    public static Map<Long, Ingredient> listIngredients = new TreeMap<>();

    final private FileServiceImpl fileService;

    public IngredientsServiceImpl(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public Ingredient getIngredients(Long ingredientId) {
        return listIngredients.get(ingredientId);
    }

    @Override
    public Ingredient editIngredients(Long ingredientId, Ingredient ingredients) {
        if (listIngredients.containsKey(ingredientId)) {
            listIngredients.put(ingredientId, ingredients);
            saveToFile();
            return ingredients;
        }
        return null;
    }

    @Override
    public long addIngredients(Ingredient ingredients) {
        listIngredients.put(ingredientId, ingredients);
        saveToFile();
        return ingredientId++;
    }

    @Override
    public boolean deleteIngredient(Long ingredientId) {
        if (listIngredients.containsKey(ingredientId)) {
            listIngredients.remove(ingredientId);
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return listIngredients;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(listIngredients);
            fileService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFileIngredients();
            listIngredients = new ObjectMapper().readValue(json, new TypeReference <TreeMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}



