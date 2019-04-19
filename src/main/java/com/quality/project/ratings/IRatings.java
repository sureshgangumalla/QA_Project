package com.quality.project.ratings;

public interface IRatings {	
	public int getUserId();
	public int getRecipeId();
	public int getRatings();
	public String getRecipeName();
	public String getRecipeNameFromRatings();
	public String getImageLocation();
	
	public void setUserId(int userName);
	public void setRecipeId(int recipeId);
	public void setRatings(int rating);
	public void setRecipeName(String recipeName);
	public void setRecipeNameFromRecipeId(String recipeName);	
	public void setImageLocation(String imageLocation);
}
