package com.quality.project.recipeCategory;

import java.util.ArrayList;

import com.quality.project.recipe.IRecipe;

public interface IRecipeCategoriesCRUDDataLayer {
	public void insertRecipeCategories(int createdRecipeID, String category);

	public boolean deleteRecipeCategories(IRecipe recipe);

	public ArrayList<String> getCategoriesForRecipe(int id);
}
