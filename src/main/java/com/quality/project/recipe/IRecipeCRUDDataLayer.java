package com.quality.project.recipe;

public interface IRecipeCRUDDataLayer {
	public int addNewRecipe(IRecipe recipe);

	public boolean updateRecipe(IRecipe recipe);

	public IRecipe getRecipe(int id);

	public boolean DeleteRecipeDetails(IRecipe recipe);
}
