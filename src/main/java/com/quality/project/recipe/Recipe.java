package com.quality.project.recipe;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.quality.project.image.IImages;

public class Recipe implements IRecipe {

	private int recipeId;
	private String recipeName;
	private String cuisine;
	private ArrayList<String> categories;
	private String description;
	private String ingredients;
	private String steps;
	private String uploaderName;
	private float rating;
	private float averageRating;
	private ArrayList<String> defaultcategories;
	private int userId;
	private Timestamp lastUpdateTime;
	private String status;
	private String rejectionReason;
	private ArrayList<IImages> images;

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public ArrayList<String> getDefaultcategories() {
		return defaultcategories;
	}

	public void setDefaultcategories(ArrayList<String> defaultcategories) {
		this.defaultcategories = defaultcategories;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public ArrayList<IImages> getImages() {
		return images;
	}

	public void setImages(ArrayList<IImages> images) {
		this.images = images;
	}

	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	@Override
	public int compareTo(IRecipe recipe) {
		if (lastUpdateTime == recipe.getLastUpdateTime()) {
			return 0;
		} else if (lastUpdateTime.before(recipe.getLastUpdateTime())) {
			return -1;
		} else {
			return 1;
		}

	}

}
