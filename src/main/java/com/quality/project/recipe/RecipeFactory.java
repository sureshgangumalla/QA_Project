package com.quality.project.recipe;

import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipeCategory.FetchCategoriesDataLayer;

public class RecipeFactory implements IRecipeFactory {

	@Override
	public IRecipe makeRecipeForRecipeId(int recipeId) {
		RecipeCRUDDataLayer recipeCRUD = new RecipeCRUDDataLayer();
		FetchCategoriesDataLayer categoryList = new FetchCategoriesDataLayer();
		ImageCRUDDataLayer imageCRUD = new ImageCRUDDataLayer();
		IRecipe recipe = new Recipe();
		recipe = recipeCRUD.getRecipe(recipeId);
		recipe.setImages(imageCRUD.getImagesForRecipes(recipeId));
		recipe.setDefaultcategories(categoryList.getAllCategoriesList());
		return recipe;
	}

	@Override
	public IRecipe makeEmptyRecipe() {
		IRecipe recipe = new Recipe();
		FetchCategoriesDataLayer Categorylist = new FetchCategoriesDataLayer();
		recipe.setDefaultcategories(Categorylist.getAllCategoriesList());
		return recipe;
	}

}
