package com.siggebig.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.siggebig.demo.model.Ingredient;
import com.siggebig.demo.model.Instruction;
import com.siggebig.demo.model.Recipe;
import com.siggebig.demo.model.Review;
import com.siggebig.demo.repository.RecipeRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RecipeRepository recipeRepository) {
		return args -> {

			Recipe spaghettiBolognese = new Recipe();
			spaghettiBolognese.setRecipeName("Pasta Bolognese");
			spaghettiBolognese.setPortions(4);
			spaghettiBolognese.setDescription("My recipe to the best bolognese ever");
			
			Instruction ins1 = new Instruction();
			ins1.setInstruction("Brown the minced meat and vegetables in the oil, add tomato puree and brown with it for a while.");

			Instruction ins2 = new Instruction();
			ins2.setInstruction("Add crushed tomatoes, chicken broth and soy, cook slowly for 15 minutes, season with salt and pepper.");

			Instruction ins3 = new Instruction();
			ins3.setInstruction("Boil pasta to taste");

			List<Instruction> spaghettiBologneseInstructions = new ArrayList<>();
			spaghettiBologneseInstructions.add(ins1);
			spaghettiBologneseInstructions.add(ins2);
			spaghettiBologneseInstructions.add(ins3);

			for (int i = 0; i < spaghettiBologneseInstructions.size(); i++) {
				Instruction ins = spaghettiBologneseInstructions.get(i);
				ins.setRecipe(spaghettiBolognese);
			
			}

			spaghettiBolognese.setInstructions(spaghettiBologneseInstructions);


			Ingredient pasta = new Ingredient();
			pasta.setName("Pasta of choice");
			pasta.setQuantity(250.0);
			pasta.setMeasure("grams");
			pasta.setRecipe(spaghettiBolognese);

			Ingredient groundBeef = new Ingredient();
			groundBeef.setName("Ground beef");
			groundBeef.setQuantity(500.0);
			groundBeef.setMeasure("grams");
			groundBeef.setRecipe(spaghettiBolognese);

			List<Ingredient> spaghettiBologneseIngredients = new ArrayList<>();
			spaghettiBologneseIngredients.add(pasta);
			spaghettiBologneseIngredients.add(groundBeef);

			spaghettiBolognese.setIngredients(spaghettiBologneseIngredients);

			Review review1 = new Review();
			review1.setComment("Best recipe ever !!");
			review1.setRecipe(spaghettiBolognese);
			Review review2 = new Review();
			review2.setComment("It is indeed bussin!");
			review2.setRecipe(spaghettiBolognese);

			List<Review> reviews = new ArrayList<>();
			reviews.add(review1);
			reviews.add(review2);
			spaghettiBolognese.setReviews(reviews);
			

			recipeRepository.save(spaghettiBolognese);

			Recipe swedishPancakes = new Recipe();
			swedishPancakes.setRecipeName("Swedish Pancakes");
			swedishPancakes.setPortions(4);
			swedishPancakes.setDescription("Banger swedish pancakes!");


			Instruction ins4 = new Instruction();
			ins4.setInstruction("Mix all ingredients, last put in the eggs");

			Instruction ins5 = new Instruction();
			ins5.setInstruction("Cook the pancake in a pan for as long as you feel, cook in butter for optimal taste.");

			Instruction ins6 = new Instruction();
			ins6.setInstruction("Eat with whatever you want");

			List<Instruction> pancakeInstructions = new ArrayList<>();
			pancakeInstructions.add(ins4);
			pancakeInstructions.add(ins5);
			pancakeInstructions.add(ins6);

			for (int i = 0; i < pancakeInstructions.size(); i++) {
				Instruction ins = pancakeInstructions.get(i);
				ins.setRecipe(swedishPancakes);
			}


			Ingredient milk = new Ingredient();
			milk.setName("Milk");
			milk.setQuantity(6);
			milk.setMeasure("dl");
			milk.setRecipe(swedishPancakes);

			Ingredient flour = new Ingredient();
			flour.setName("Flour");
			flour.setQuantity(3);
			flour.setMeasure("dl");
			flour.setRecipe(swedishPancakes);

			Ingredient egg = new Ingredient();
			egg.setName("eggs");
			egg.setQuantity(3);
			//egg.setMeasure("eggs"); //XD
			egg.setRecipe(swedishPancakes);

			Ingredient butter = new Ingredient();
			butter.setName("Butter");
			butter.setRecipe(swedishPancakes);

			List<Ingredient> swedishPancakesIngredients = new ArrayList<>();
			swedishPancakesIngredients.add(milk);
			swedishPancakesIngredients.add(flour);
			swedishPancakesIngredients.add(egg);
			swedishPancakesIngredients.add(butter);

			swedishPancakes.setIngredients(swedishPancakesIngredients);
			swedishPancakes.setInstructions(pancakeInstructions);


			Review review3 = new Review();
			review3.setComment("I love swedish pancakes!! its da best !!");
			review3.setRecipe(swedishPancakes);
			Review review4 = new Review();
			review4.setComment("Taste great with a little bit of sugar in the battern!");
			review4.setRecipe(swedishPancakes);

			List<Review> reviews2 = new ArrayList<>();
			reviews2.add(review3);
			reviews2.add(review4);
			swedishPancakes.setReviews(reviews2);

			recipeRepository.save(swedishPancakes);



			System.out.println("-------initalized database-------");

		};
	}

}
