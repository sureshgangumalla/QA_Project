package com.quality.project.recipe;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.quality.project.image.IImages;

public interface IRecipe extends Comparable<IRecipe> {

	public String getCuisine();

	public void setCuisine(String cuisine);

	public String getRecipeName();

	public void setRecipeName(String recipeName);

	public String getDescription();

	public void setDescription(String description);

	public ArrayList<String> getCategories();

	public void setCategories(ArrayList<String> categories);

	public String getSteps();

	public void setSteps(String steps);

	public String getIngredients();

	public void setIngredients(String ingredients);

	public float getRating();

	public void setRating(float rating);

	public ArrayList<String> getDefaultcategories();

	public void setDefaultcategories(ArrayList<String> defaultcategories);

	public int getUserId();

	public void setUserId(int userId);

	public Timestamp getLastUpdateTime();

	public void setLastUpdateTime(Timestamp lastUpdateTime);

	public float getAverageRating();

	public void setAverageRating(float averageRating);

	public String getStatus();

	public void setStatus(String status);

	public int getRecipeId();

	public void setRecipeId(int recipeId);

	public ArrayList<IImages> getImages();

	public void setImages(ArrayList<IImages> images);
	
	public String getUploaderName();
	
	public void setUploaderName(String uploaderName);

}
