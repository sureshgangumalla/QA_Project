package com.quality.project.recipe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteRecipeController {
	@RequestMapping(value = "/deleterecipes", method = RequestMethod.GET)
	public ModelAndView showForms() {
		FetchRecipesDataLayer fetchRecipes = new FetchRecipesDataLayer();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("deleterecipespage");
		mav.addObject("recipes", fetchRecipes.getAllRecipes());
		return mav;
	}

	@RequestMapping(value = "/delete-recipe", method = RequestMethod.POST)
	public ModelAndView deleteRecipe(HttpServletRequest request, @RequestParam int recipeId) {
		ModelAndView mav = new ModelAndView();
		IDelete deleteRecipe = new DeleteRecipe();
		IRecipeFactory recipeFactory = new RecipeFactory();
		IRecipe recipeToBeDeleted = recipeFactory.makeRecipeForRecipeId(recipeId);
		if (deleteRecipe.DeleteWholeRecipe(recipeToBeDeleted)) {
			mav.setViewName("redirect:/deleterecipes");
			return mav;
		}
		return mav;
	}
}
