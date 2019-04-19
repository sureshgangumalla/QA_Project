package com.quality.project.recipe;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class RecipeValidator implements IRecipeValidator {
	private Map<String, String> errorMessages;

	public RecipeValidator() {
		this.errorMessages = new HashMap<String, String>();
	}

	@Override
	public boolean isValidRecipe(IRecipe recipe) {

		boolean validity = true;
		if (recipe.getRecipeName().isEmpty()) {
			validity = false;
			errorMessages.put("recipeName", "Please provide recipe name");
		}

		if (0 == recipe.getCategories().size()) {
			validity = false;
			errorMessages.put("categories", "Please select Categories");
		}
		if (recipe.getSteps().isEmpty()) {
			validity = false;
			errorMessages.put("steps", "Please provide steps");
		}
		if (recipe.getIngredients().isEmpty()) {
			validity = false;
			errorMessages.put("ingredients", "Please provide ingredients");
		}
		return validity;

	}

	public boolean isNotADuplicateRecipe(IRecipe recipe) {
		FetchRecipesDataLayer fetchRecipes = new FetchRecipesDataLayer();
		IRecipe exsistingRecipe = fetchRecipes.getRecipeWithSameRecipeNameAndDifferentRecipeID(recipe);

		if (0 == exsistingRecipe.getRecipeId()) {
			return true;
		}
		errorMessages.put("recipeName", "Recipe Name Already exsists");
		return false;
	}

	@Override
	public Map<String, String> getErrors() {
		return errorMessages;
	}
}
