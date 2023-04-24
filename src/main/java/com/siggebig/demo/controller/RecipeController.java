package com.siggebig.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;

import com.siggebig.demo.model.Recipe;
import com.siggebig.demo.repository.RecipeRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();


        if(recipes.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .header("x-info", "No recipes found in db")
                    .build();
        } else {
            return ResponseEntity.ok(recipes);
        }
    }
    
    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable long id) {
        return recipeRepository.findById(id);
    }
    
    @PostMapping
    public ResponseEntity<List<Recipe>> createRecipe(@RequestBody Recipe recipe) {
        recipeRepository.save(recipe);
        return getAllRecipes();
    }
    
 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable long recipeId) {
        recipeRepository.deleteById(recipeId);
        return ResponseEntity.ok("Recipe successfully deleted");
    }
}
