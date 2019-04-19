package com.quality.project.image;

import java.util.ArrayList;

public interface IImageCRUDDataLayer {
	public boolean addImageForRecipe(int createdRecipeID, IImages image);

	public ArrayList<IImages> getImagesForRecipes(int recipeId);

	public boolean DeleteImagesForRecipe(int recipeID);
}
