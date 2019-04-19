package com.quality.project.view;

import org.springframework.web.servlet.ModelAndView;

import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipe.IRecipe;
import com.quality.project.recipe.Recipe;
import com.quality.project.recipe.RecipeCRUDDataLayer;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.templates.FollowersDatalayer;
import com.quality.project.user.IUser;
import com.quality.project.user.User;

public class LoadUserView {

	public ModelAndView LoadView(int recipeId) throws Exception {
		ModelAndView mav = new ModelAndView();
		RecipeCRUDDataLayer recipeTemplate = new RecipeCRUDDataLayer();
		ImageCRUDDataLayer imagesTemplate = new ImageCRUDDataLayer();
		FollowersDatalayer followersDatalayer = new FollowersDatalayer();
		IRecipe recipe = new Recipe();
		IUser user = new User();
		ISessionHandler session = SessionHandler.getSession();
		user = session.getUserIntheSession();

		if (session.isSessionStarted() && !session.isAdminInSession()) {
			mav.setViewName("recipedescription");
			recipe = recipeTemplate.getRecipe(recipeId);

			if (user.getUserID() == recipe.getUserId()) {
				mav.addObject("OwnRecipe", "Edit");
			} else {

				if (followersDatalayer.IsFollowing(user.getUserID(), recipe.getUserId())) {
					mav.addObject("follow", "FOLLOWING");
				} else {
					mav.addObject("follow", "NOT_FOLLOWING");
				}
				mav.addObject("rating", "Rating");

			}
			mav.addObject("recipe", recipe);
			mav.addObject("images", imagesTemplate.getImagesForRecipes(recipeId));

		} else if (session.isAdminInSession() || !session.isSessionStarted()) {
			mav.setViewName("recipedescription");
			mav.addObject("images", imagesTemplate.getImagesForRecipes(recipeId));
			mav.addObject("recipe", recipeTemplate.getRecipe(recipeId));
		}
		return mav;
	}

}
