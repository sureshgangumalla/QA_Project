package com.quality.project.ratings;

public class Ratings implements IRatings{
	private int recipeRatings;
	private int userId;
	private int recipeId;
	private String recipeImage;
	String recipeName;	
	@Override
	public int getUserId() {
		return userId;
	}
	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int getRecipeId() {
		return recipeId;
	}
	@Override
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}	
	@Override
	public String getRecipeName() {
		return recipeName;
	}
	@Override
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	@Override
	public String getRecipeNameFromRatings() {
		return recipeName;
	}
	@Override
	public void setRecipeNameFromRecipeId(String recipeName) {
		this.recipeName = recipeName;
	}
	@Override
	public int getRatings() {
		return recipeRatings;
	}
	@Override
	public void setRatings(int ratingParameter) {
		this.recipeRatings = ratingParameter;		
	}
	@Override
	public String getImageLocation() {
		return recipeImage;
	}
	@Override
	public void setImageLocation(String imageLocation) {
		this.recipeImage = imageLocation;
	}
}
