package com.quality.project.recipe;

public interface IDelete {
	public boolean DeleteRecipeCategroies(IRecipe recipe);

	public boolean DeleteRecipeImages(IRecipe recipe);

	public boolean DeleteRecipeDetails(IRecipe recipe);

	public boolean DeleteRecipeRatings(IRecipe recipe);

	public boolean DeleteWholeRecipe(IRecipe recipe);
}
