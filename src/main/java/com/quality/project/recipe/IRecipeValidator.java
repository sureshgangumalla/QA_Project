package com.quality.project.recipe;

import java.util.Map;

public interface IRecipeValidator {
	public Map<String, String> getErrors();

	public boolean isValidRecipe(IRecipe recipe);

	public boolean isNotADuplicateRecipe(IRecipe recipe);

}
