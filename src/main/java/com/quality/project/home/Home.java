package com.quality.project.home;

import java.util.HashMap;
import java.util.List;

import com.quality.project.ratings.IRatings;
import com.quality.project.recipe.FetchRecipesDataLayer;

public class Home {
	public List<IRatings> getPopularProducts() {
		FetchRecipesDataLayer fetchRecipes = new FetchRecipesDataLayer();
		List<IRatings> popularProductsList = fetchRecipes.mostPopularProducts();
		HashMap<Integer, String> popularProductHashmap = new HashMap<Integer, String>();
		for (int i = 0; i < popularProductsList.size(); i++) {
			popularProductHashmap.put(popularProductsList.get(i).getRecipeId(),
					popularProductsList.get(i).getRecipeName());
		}
		return null;
	}

}
