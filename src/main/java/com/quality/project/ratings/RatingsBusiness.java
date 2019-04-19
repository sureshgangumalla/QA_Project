package com.quality.project.ratings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quality.project.recipe.ISortRecipesWithTime;
import com.quality.project.recipe.SortRecipesWithTime;

public class RatingsBusiness {
	String recipeName;	
	HashMap<Integer, Integer> calculateAvgRatings= new HashMap<Integer, Integer>();
	HashMap<Integer, String> mapRecipeIdToRecipeName =new HashMap<Integer, String>();
	List<IRatings> ratingsList = new ArrayList<IRatings>();	
	public List<IRatings> getRatingsAverage() throws Exception { 
		try {
			RatingDataLayerTemplate ratings = new RatingDataLayerTemplate();	
			List<IRatings> ratingList = ratings.Select();
			
			for (int i = 0; i < ratingList.size(); i++) {
				int recipeId = ratingList.get(i).getRecipeId();
				int recipeRatings = ratingList.get(i).getRatings();
				String recipeName = ratingList.get(i).getRecipeName();
				calculateAvgRatingForCurrentRecipe(recipeId, recipeRatings, recipeName);			
			}			
			calculateAvgRatings = sortRecipesAccordingToRatings();			
			ratingsList = placeAvgRatingsInRatingsList(calculateAvgRatings,mapRecipeIdToRecipeName);			
			return ratingsList;
		}catch(Exception e) {
			throw e;
		}		
	}
	public List<IRatings> placeAvgRatingsInRatingsList(HashMap<Integer, Integer> calculateAvgRatings, HashMap<Integer, String> mapRecipeIdToRecipeName) {
		
		for (Map.Entry<Integer, Integer> entry : calculateAvgRatings.entrySet()) {
			IRatings ratingsObj = new Ratings();
			ratingsObj.setRecipeId(entry.getKey());
			ratingsObj.setRatings(entry.getValue());
			ratingsObj.setRecipeName(mapRecipeIdToRecipeName.get(entry.getKey()));
			ratingsList.add(ratingsObj);
		}
		return ratingsList;
		
	}
	public HashMap<Integer, Integer> sortRecipesAccordingToRatings() {
		ISortRecipesWithTime sortedAvgRatings = new SortRecipesWithTime();
		calculateAvgRatings = sortedAvgRatings.sortByValue(calculateAvgRatings);
		return calculateAvgRatings;
	}
	public void calculateAvgRatingForCurrentRecipe(int recipeId, int recipeRatings, String recipeName) {
		if(!calculateAvgRatings.containsKey(recipeId)) {
			calculateAvgRatings.put(recipeId, recipeRatings);
			mapRecipeIdToRecipeName.put(recipeId,recipeName);
		}else {
			int currentRatingsValue = calculateAvgRatings.get(recipeId);
			currentRatingsValue = (currentRatingsValue+recipeRatings)/2;
			calculateAvgRatings.put(recipeId, currentRatingsValue);
			mapRecipeIdToRecipeName.put(recipeId,recipeName);
		}		
	}
}
