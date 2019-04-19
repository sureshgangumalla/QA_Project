package com.quality.project.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.ratings.RatingDataLayerTemplate;
import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.templates.FollowersDatalayer;
import com.quality.project.user.IUser;

@Controller
public class RecipeDetailsController {

	private RecipeCRUDDataLayer recipeTemplate;
	private ImageCRUDDataLayer imagesTemplate;
	private FollowersDatalayer followersDatalayer;
	private RatingDataLayerTemplate ratings;
	private IUser user;
	private ISessionHandler session;

	public RecipeDetailsController() {
		recipeTemplate = new RecipeCRUDDataLayer();
		ratings = new RatingDataLayerTemplate();
		imagesTemplate = new ImageCRUDDataLayer();
		followersDatalayer = new FollowersDatalayer();
		session = SessionHandler.getSession();

	}

	@PostMapping(value = "/recipedescription")
	public ModelAndView GiveRating(@RequestParam int rating, @RequestParam int recipeId) throws Exception {

		user = session.getUserIntheSession();
		ratings.executeGiveRating(recipeId, user.getUserID(), rating);
		ModelAndView mav = new ModelAndView();
		mav = this.LoadView(recipeId);
		return mav;

	}

	@PostMapping(value = "/followUser")
	public ModelAndView FollowUser(@RequestParam int recipeId) throws Exception {

		user = session.getUserIntheSession();
		followersDatalayer.FollowUser(user.getUserID(), recipeTemplate.getRecipe(recipeId).getUserId());
		ModelAndView mav = new ModelAndView();
		mav = this.LoadView(recipeId);
		return mav;

	}

	public ModelAndView LoadView(int recipeId) throws Exception {

		IRecipe recipe = new Recipe();
		user = session.getUserIntheSession();

		ModelAndView mav = new ModelAndView();

		recipe = recipeTemplate.getRecipe(recipeId);
		System.out.println(recipe.getRecipeId());
		mav.setViewName("recipedescription");

		if (user.getUserID() == (recipe.getUserId())) {
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

		return mav;
	}
}