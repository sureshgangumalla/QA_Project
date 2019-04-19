package com.quality.project.recipe;

import java.util.ArrayList;

import com.quality.project.image.DeleteImageFile;
import com.quality.project.image.IImages;
import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipeCategory.RecipeCategoriesCRUDDataLayer;
import com.quality.project.ratings.RatingDataLayerTemplate;

public class DeleteRecipe implements IDelete {
	private RecipeCategoriesCRUDDataLayer categoryCRUD;
	private ImageCRUDDataLayer imagesCRUD;
	private RecipeCRUDDataLayer recipeCRUD;
	private DeleteImageFile deleteImageFile;
	private RatingDataLayerTemplate ratingsCRUD;

	DeleteRecipe() {
		categoryCRUD = new RecipeCategoriesCRUDDataLayer();
		imagesCRUD = new ImageCRUDDataLayer();
		recipeCRUD = new RecipeCRUDDataLayer();
		ratingsCRUD = new RatingDataLayerTemplate();
		deleteImageFile = new DeleteImageFile();
	}

	@Override
	public boolean DeleteWholeRecipe(IRecipe recipe) {

		boolean isCategoriesDeleted = DeleteRecipeCategroies(recipe);

		boolean isImagesDeleted = DeleteRecipeImages(recipe);

		boolean isRatingsDeleted = DeleteRecipeRatings(recipe);

		boolean isRecipeDetailsDeleted = DeleteRecipeDetails(recipe);

		return isCategoriesDeleted && isImagesDeleted && isRatingsDeleted && isRecipeDetailsDeleted;
	}

	@Override
	public boolean DeleteRecipeCategroies(IRecipe recipe) {
		if (categoryCRUD.deleteRecipeCategories(recipe)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteRecipeImages(IRecipe recipe) {
		ArrayList<IImages> imagesToBeDeleted = imagesCRUD.getImagesForRecipes(recipe.getRecipeId());

		for (IImages deletedImage : imagesToBeDeleted) {
			deleteImageFile.delete(deletedImage);
		}

		if (imagesCRUD.DeleteImagesForRecipe(recipe.getRecipeId())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteRecipeDetails(IRecipe recipe) {
		if (recipeCRUD.DeleteRecipeDetails(recipe)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteRecipeRatings(IRecipe recipe) {
		if (ratingsCRUD.DeleteRecipeRatings(recipe)) {
			return true;
		}
		return false;
	}

}