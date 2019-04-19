package com.quality.project.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.image.ImagesToBeAddedForRecipe;
import com.quality.project.recipe.GetRecipesForUser;
import com.quality.project.recipe.IRecipe;
import com.quality.project.recipe.IRecipeFactory;
import com.quality.project.recipe.RecipeFactory;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;

@Controller
public class UserPageController {

	@RequestMapping(value = "/userpage", method = RequestMethod.GET)
	public ModelAndView showForms() {
		GetRecipesForUser sortrecipes = new GetRecipesForUser();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userpage");
		IUser loggedInUser = new User();
		ISessionHandler session = SessionHandler.getSession();
		loggedInUser = session.getUserIntheSession();
		mav.addObject("pendingRecipes", sortrecipes.getPendingRecipes(loggedInUser));
		mav.addObject("approvedRecipes", sortrecipes.getApprovedRecipes(loggedInUser));
		mav.addObject("rejectedRecipes", sortrecipes.getRejectedRecipes(loggedInUser));
		return mav;
	}

	@RequestMapping(value = "/submitAddRecipe", method = RequestMethod.POST)
	public ModelAndView submitAddRecipe(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/addrecipe");
		return mav;
	}

	@RequestMapping(value = "/submitRecipeID", method = RequestMethod.POST)
	public ModelAndView submitUserRecipe(HttpServletRequest request, @RequestParam int recipeId) throws Exception {
		ModelAndView mav = new ModelAndView();
		ImagesToBeAddedForRecipe imagesToBeSaved = ImagesToBeAddedForRecipe.getImagesToBeSavedInstance();
		IRecipeFactory recipeFactory = new RecipeFactory();
		IRecipe selectedRecipe = recipeFactory.makeRecipeForRecipeId(recipeId);
		if (selectedRecipe.getStatus().equals("approved")) {
			mav.addObject("recipe", selectedRecipe);
			mav.setViewName("recipedescription");
			return mav;
		} else {
			imagesToBeSaved.setImages(selectedRecipe.getImages());
			mav.addObject("recipe", selectedRecipe);
			mav.setViewName("addrecipe");
			return mav;
		}

	}
}
