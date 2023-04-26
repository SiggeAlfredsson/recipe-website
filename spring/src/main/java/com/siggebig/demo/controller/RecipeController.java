package com.siggebig.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.siggebig.demo.model.Recipe;
import com.siggebig.demo.model.Review;
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

    @PostMapping("/review/{recipeId}/{reviewComment}") // Requestbody bättre?
    public String addReview(@PathVariable long recipeId, @PathVariable String reviewComment) {

        Recipe recipe = recipeRepository.getReferenceById(recipeId);



        Review review = new Review(); // nasty lösning, förbättra?
        review.setComment(reviewComment);
        review.setRecipe(recipe);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review); 
        recipe.setReviews(reviews);
        recipeRepository.save(recipe);

        return "Review added";
    } 

    @GetMapping("/image/{recipeId}")
    public ResponseEntity<byte[]> uploadImage(@PathVariable long recipeId) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("images/image"+recipeId+".jpg").getFile());
        byte[] fileContent = Files.readAllBytes(file.toPath()); //få detta funka med LOB ??
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
    
    // @PostMapping
    // public ResponseEntity<List<Recipe>> createRecipe(@RequestBody Recipe recipe) {
    //     recipeRepository.save(recipe);
    //     return getAllRecipes();
    // }
    
 
    
    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteRecipe(@PathVariable long recipeId) {
    //     recipeRepository.deleteById(recipeId);
    //     return ResponseEntity.ok("Recipe successfully deleted");
    // }
}
