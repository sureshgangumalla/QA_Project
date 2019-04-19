package com.quality.project.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.recipe.GetRecipesForUser;

@Controller
public class RecipesToBeApprovedController {
	@RequestMapping(value = "/approverecipes", method = RequestMethod.GET)
	public ModelAndView showForms() {
		GetRecipesForUser sortrecipes = new GetRecipesForUser();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("approverecipes");
		mav.addObject("recipes", sortrecipes.getLatestPendingApprovalRecipes());
		return mav;
	}

}
