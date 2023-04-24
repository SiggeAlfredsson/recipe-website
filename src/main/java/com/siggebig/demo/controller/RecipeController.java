package com.siggebig.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.siggebig.demo.model.Recipe;
import com.siggebig.demo.repository.RecipeRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable long id) {
        return recipeRepository.findById(id);
    }
    
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    // @PutMapping("/{id}")
    // public Recipe updateRecipe(@PathVariable long id, @RequestBody Recipe newRecipe) {
    //     return recipeRepository.findById(id)
    //             .map(recipe -> {
    //                 recipe.setRecipeName(newRecipe.getRecipeName());
    //                 recipe.setIngredients(newRecipe.getIngredients());
    //                 recipe.setRatings(newRecipe.getRatings());
    //                 return recipeRepository.save(recipe);
    //             })
    //             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    // }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id) {
        recipeRepository.deleteById(id);
    }
}
