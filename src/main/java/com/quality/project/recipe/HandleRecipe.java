package com.quality.project.recipe;

import java.util.Map;

import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipeCategory.RecipeCategoriesCRUDDataLayer;

public abstract class HandleRecipe {

	RecipeValidator validator;
	ImageCRUDDataLayer imageCRUD;
	RecipeCRUDDataLayer recipeCRUD;
	RecipeCategoriesCRUDDataLayer categoryCRUD;

	public HandleRecipe() {
		validator = new RecipeValidator();
		recipeCRUD = new RecipeCRUDDataLayer();
		categoryCRUD = new RecipeCategoriesCRUDDataLayer();
		imageCRUD = new ImageCRUDDataLayer();
	}

	public boolean manageRecipe(IRecipe recipe) {
		if (validator.isValidRecipe(recipe) && validator.isNotADuplicateRecipe(recipe)) {
			manageRecipeDetails(recipe);
			manageRecipeCategories(recipe);
			manageRecipeImages(recipe);
			return true;
		}
		return false;
	}

	abstract void manageRecipeDetails(IRecipe recipe);

	abstract void manageRecipeCategories(IRecipe recipe);

	abstract void manageRecipeImages(IRecipe recipe);

	public Map<String, String> RecipeErrors() {
		return validator.getErrors();
	}
}
