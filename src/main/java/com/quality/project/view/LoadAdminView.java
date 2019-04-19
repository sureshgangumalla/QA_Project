package com.quality.project.view;

import org.springframework.web.servlet.ModelAndView;

import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipe.RecipeCRUDDataLayer;

public class LoadAdminView {

	public ModelAndView AdminView(int recipeId) {
		RecipeCRUDDataLayer template = new RecipeCRUDDataLayer();
		ImageCRUDDataLayer imagesTemplate = new ImageCRUDDataLayer();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminApproveRecipe");
		mav.addObject("recipe", template.getRecipe(recipeId));
		mav.addObject("images", imagesTemplate.getImagesForRecipes(recipeId));
		return mav;
	}
}
