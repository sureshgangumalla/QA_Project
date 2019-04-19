package com.quality.project.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.recipe.GetRecipesForUser;
import com.quality.project.recipe.IRecipe;
import com.quality.project.recipe.RecipeCRUDDataLayer;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.view.LoadAdminView;

@Controller
public class ApproveController {

	ISessionHandler sessionHandler = SessionHandler.getSession();

	@RequestMapping(value = "/approve-recipe", method = RequestMethod.POST)
	public ModelAndView AdminApproveRecipe(@RequestParam int recipeId) throws Exception {
		LoadAdminView loadAdminView = new LoadAdminView();
		ModelAndView mav = loadAdminView.AdminView(recipeId);
		return mav;
	}

	@PostMapping("/recipe-response")
	public ModelAndView AdminRecipeResponse(@RequestParam String response, @RequestParam String submit,
			@RequestParam int recipeId) {
		RecipeCRUDDataLayer template = new RecipeCRUDDataLayer();
		ImageCRUDDataLayer imagesTemplate = new ImageCRUDDataLayer();
		AdminApprove adminApprove = new AdminApprove();
		adminApprove.GiveAdminResponse(recipeId, response, submit);
		ModelAndView mav = new ModelAndView();
		GetRecipesForUser sortRecipes = new GetRecipesForUser();
		IRecipe recipe = sortRecipes.getRecipeToBeApproved();

		mav.addObject("recipe", template.getRecipe(recipe.getRecipeId()));
		mav.addObject("images", imagesTemplate.getImagesForRecipes(recipe.getRecipeId()));
		mav.setViewName("adminApproveRecipe");
		return mav;
	}

}
