package com.quality.project.recipe;

import java.util.*;

import com.quality.project.user.IUser;

public class GetRecipesForUser {
	private FetchRecipesDataLayer fetchRecipes;
	private List<IRecipe> recipeList;

	public GetRecipesForUser() {
		fetchRecipes = new FetchRecipesDataLayer();
	}

	public List<IRecipe> getLatestPendingApprovalRecipes()

	{
		recipeList = fetchRecipes.getRecipesToBeApproved();
		Collections.sort(recipeList);
		return recipeList;
	}

	public IRecipe getRecipeToBeApproved() {
		recipeList = fetchRecipes.getRecipesToBeApproved();
		Collections.sort(recipeList);
		return recipeList.get(0);
	}

	public List<IRecipe> getApprovedRecipes(IUser user) {
		return getRecipes("approved", fetchRecipes.getRecipesByUser(user));
	}

	public List<IRecipe> getPendingRecipes(IUser user) {
		return getRecipes("pending", fetchRecipes.getRecipesByUser(user));
	}

	public List<IRecipe> getRejectedRecipes(IUser user) {
		return getRecipes("rejected", fetchRecipes.getRecipesByUser(user));
	}

	private List<IRecipe> getRecipes(String status, List<IRecipe> recipes) {
		List<IRecipe> approvedrecipes = new ArrayList<IRecipe>();
		for (IRecipe recipe : recipes) {
			if (recipe.getStatus().equals(status)) {
				approvedrecipes.add(recipe);
			}
		}
		return approvedrecipes;
	}

}
