package com.quality.project.recipe;

public interface IRecipeFactory {
	public IRecipe makeRecipeForRecipeId(int recipeId);

	public IRecipe makeEmptyRecipe();
}
