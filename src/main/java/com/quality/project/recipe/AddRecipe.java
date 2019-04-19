package com.quality.project.recipe;

import com.quality.project.image.IImages;

public class AddRecipe extends HandleRecipe {
	private int createdRecipeID;

	@Override
	void manageRecipeDetails(IRecipe recipe) {
		recipeCRUD.addNewRecipe(recipe);
		createdRecipeID = recipeCRUD.getRecipeID(recipe);
	}

	@Override
	void manageRecipeCategories(IRecipe recipe) {

		for (String category : recipe.getCategories()) {
			categoryCRUD.insertRecipeCategories(createdRecipeID, category);
		}
	}

	@Override
	void manageRecipeImages(IRecipe recipe) {
		for (IImages image : recipe.getImages()) {
			imageCRUD.addImageForRecipe(createdRecipeID, image);
		}
	}

}
