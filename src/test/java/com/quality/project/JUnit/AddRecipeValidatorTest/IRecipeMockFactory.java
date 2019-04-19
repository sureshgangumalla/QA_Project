package com.quality.project.JUnit.AddRecipeValidatorTest;

import com.quality.project.recipe.IRecipe;

public interface IRecipeMockFactory {
	public IRecipe getRecipes(String type);
}
