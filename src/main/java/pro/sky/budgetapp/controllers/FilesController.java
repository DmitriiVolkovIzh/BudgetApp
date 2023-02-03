package pro.sky.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.budgetapp.services.FileService;

import java.io.*;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Скачивание и загрузка файлов")
public class FilesController {

    public final FileService fileService;

    @GetMapping("/export")
    @Operation(summary = "Скачать все рецепты")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileService.getRecipesFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/import/ingredients",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл ингридиенты")
    public ResponseEntity<Void> upLoadIngredientsFile(@RequestParam MultipartFile file) {
        fileService.cleanFileIngredient();
        File dataFile = fileService.getIngredientsFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping(value = "/import/recipes",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл рецепты")
    public ResponseEntity<Void> upLoadRecipesFile(@RequestParam MultipartFile file) {
        fileService.cleanFileRecipe();
        File dataFile = fileService.getRecipesFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}

