package com.siggebig.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.siggebig.demo.model.Ingredient;
import com.siggebig.demo.model.Recipe;
import com.siggebig.demo.repository.RecipeRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RecipeRepository recipeRepository) {
		return args -> {

			System.out.println("-------initalized database-------");

			Recipe spaghettiBolognese = new Recipe();
			spaghettiBolognese.setRecipeName("Spaghetti Bolognese");
			spaghettiBolognese.setPortions(4);

			Ingredient spaghetti = new Ingredient();
			spaghetti.setName("Spaghetti");
			spaghetti.setQuantity(250.0);
			spaghetti.setMeasure("grams");
			spaghetti.setRecipe(spaghettiBolognese);

			Ingredient groundBeef = new Ingredient();
			groundBeef.setName("Ground beef");
			groundBeef.setQuantity(500.0);
			groundBeef.setMeasure("grams");
			groundBeef.setRecipe(spaghettiBolognese);

			List<Ingredient> spaghettiBologneseIngredients = new ArrayList<>();
			spaghettiBologneseIngredients.add(spaghetti);
			spaghettiBologneseIngredients.add(groundBeef);

			spaghettiBolognese.setIngredients(spaghettiBologneseIngredients);

			recipeRepository.save(spaghettiBolognese);

			Recipe swedishPancakes = new Recipe();
			swedishPancakes.setRecipeName("Swedish Pancakes");
			swedishPancakes.setPortions(4);

			Ingredient milk = new Ingredient();
			milk.setName("Milk");
			milk.setQuantity(6);
			milk.setMeasure("dl");
			milk.setRecipe(swedishPancakes);

			Ingredient flour = new Ingredient();
			flour.setName("flour");
			flour.setQuantity(3);
			flour.setMeasure("dl");
			flour.setRecipe(swedishPancakes);

			Ingredient egg = new Ingredient();
			egg.setName("egg");
			egg.setQuantity(3);
			egg.setMeasure("eggs"); //XD
			egg.setRecipe(swedishPancakes);

			List<Ingredient> swedishPancakesIngredients = new ArrayList<>();
			swedishPancakesIngredients.add(milk);
			swedishPancakesIngredients.add(flour);
			swedishPancakesIngredients.add(egg);

			swedishPancakes.setIngredients(swedishPancakesIngredients);

			recipeRepository.save(swedishPancakes);



		};
	}

}
