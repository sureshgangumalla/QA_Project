package com.quality.project.search;

public interface ISearchBuilder {
	public void buildRecipeName(String recipeName);
	public void buildRecipeId(int recipeId);
	
	public String getBuildRecipeName();
	public int getBuildRecipeId();
}
