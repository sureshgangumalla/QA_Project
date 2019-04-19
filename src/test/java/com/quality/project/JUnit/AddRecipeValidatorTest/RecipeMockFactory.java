package com.quality.project.JUnit.AddRecipeValidatorTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.quality.project.recipe.IRecipe;
import com.quality.project.recipe.Recipe;

public class RecipeMockFactory implements IRecipeMockFactory {
	private Map<String, IRecipe> recipes;
	private ArrayList<String> categories;

	public RecipeMockFactory() throws Exception {
		this.recipes = new HashMap<String, IRecipe>();
		this.createRecipes();

	}

	public void createRecipes() throws Exception {

		IRecipe emptyRecipeName = new Recipe();
		emptyRecipeName.setRecipeName("");
		emptyRecipeName.setCuisine("indian");
		emptyRecipeName.setSteps("cookfor5min");
		emptyRecipeName.setIngredients("chicken");
		categories = new ArrayList<String>();
		emptyRecipeName.setCategories(categories);

		recipes.put("emptyRecipeName", emptyRecipeName);

		IRecipe emptyCusine = new Recipe();
		emptyCusine.setRecipeName("test");
		emptyCusine.setCuisine("");
		emptyCusine.setSteps("cookfor5min");
		emptyCusine.setIngredients("chicken");
		categories = new ArrayList<String>();
		categories.add("testCategoriers");
		emptyCusine.setCategories(categories);
		

		recipes.put("emptyCusine", emptyCusine);

		IRecipe emptyIngredients = new Recipe();
		emptyIngredients.setRecipeName("indian");
		emptyIngredients.setSteps("cookfor5min");
		emptyIngredients.setCuisine("chicken");
		emptyIngredients.setIngredients("");
		categories = new ArrayList<String>();
		
		emptyIngredients.setCategories(categories);

		recipes.put("emptyIngredients", emptyIngredients);

		IRecipe validRecipe = new Recipe();
		validRecipe.setRecipeName("valid");
		validRecipe.setCuisine("indian");
		validRecipe.setSteps("cookfor5min");
		validRecipe.setIngredients("chicken");
		categories = new ArrayList<String>();
		categories.add("testCategoriers");
		validRecipe.setCategories(categories);

		recipes.put("validRecipe", validRecipe);

		IRecipe invalidRecipe = new Recipe();
		invalidRecipe.setRecipeName("");
		invalidRecipe.setCuisine("indian");
		invalidRecipe.setIngredients("");
		invalidRecipe.setSteps("");
		categories = new ArrayList<String>();
		invalidRecipe.setCategories(categories);

		recipes.put("invalidRecipe", invalidRecipe);

	}

	public IRecipe getRecipes(String type) {
		return recipes.get(type);
	}

}
