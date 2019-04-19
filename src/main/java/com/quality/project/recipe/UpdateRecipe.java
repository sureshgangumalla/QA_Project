package com.quality.project.recipe;

import com.quality.project.image.IImages;

public class UpdateRecipe extends HandleRecipe {
	private int recipeID;

	@Override
	void manageRecipeDetails(IRecipe recipe) {
		recipeCRUD.updateRecipe(recipe);
		recipeID = recipe.getRecipeId();
	}

	@Override
	void manageRecipeCategories(IRecipe recipe) {
		categoryCRUD.deleteRecipeCategories(recipe);
		for (String category : recipe.getCategories()) {
			categoryCRUD.insertRecipeCategories(recipe.getRecipeId(), category);
		}
	}

	@Override
	void manageRecipeImages(IRecipe recipe) {
		imageCRUD.DeleteImagesForRecipe(recipeID);
		for (IImages image : recipe.getImages()) {
			imageCRUD.addImageForRecipe(recipe.getRecipeId(), image);
		}

	}

}
