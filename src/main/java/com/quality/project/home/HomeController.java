package com.quality.project.home;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.access.AccessControlProxy;
import com.quality.project.access.IAccessControl;
import com.quality.project.ratings.RatingsBusiness;
import com.quality.project.recipe.FetchRecipesDataLayer;
import com.quality.project.recipe.IRecipe;
import com.quality.project.recipeCategory.FetchCategoriesDataLayer;
import com.quality.project.recipe.RecipeCRUDDataLayer;
import com.quality.project.recipeCategory.FetchCategoriesDataLayer;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.user.IUser;
import com.quality.project.view.LoadUserView;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public ModelAndView redirectToHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/home");
		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() throws Exception {

		RatingsBusiness ratingTemplate = new RatingsBusiness();
		FetchRecipesDataLayer fetchRecipes = new FetchRecipesDataLayer();
		FetchCategoriesDataLayer fetchCategories = new FetchCategoriesDataLayer();

		ISessionHandler session = SessionHandler.getSession();
		IUser loggedInUser = session.getUserIntheSession();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("categories", fetchCategories.getCategoriesWithRecipes());
		mav.addObject("ratingValues", ratingTemplate.getRatingsAverage());
		mav.addObject("newArrivals", fetchRecipes.mostPopularProducts());

		IAccessControl AccesControl = new AccessControlProxy(loggedInUser);
		ArrayList<String> loggedInUserTools = new ArrayList<String>();
		if (null != AccesControl.accessAdminTools()) {
			loggedInUserTools = AccesControl.accessAdminTools();
		}
		if (null != AccesControl.accessUserTools()) {
			loggedInUserTools = AccesControl.accessUserTools();
		}
		mav.addObject("tools", loggedInUserTools);
		return mav;
	}

	@RequestMapping(value = "/recipeDesc", method = RequestMethod.POST)
	public ModelAndView handleRecipe(HttpServletRequest request, @RequestParam int recipeId) throws Exception {
		LoadUserView loadUserView = new LoadUserView();
		ModelAndView mav = loadUserView.LoadView(recipeId);
		return mav;
	}

	@RequestMapping(value = "/userhome", method = RequestMethod.POST)
	public ModelAndView userHome(HttpServletRequest request, @RequestParam String action) {

		ModelAndView mav = new ModelAndView();
		if (action.equals("ApproveRecipes")) {
			mav.setViewName("redirect:/approverecipes");
			return mav;
		} else if (action.equals("ConfigurePassword")) {
			mav.setViewName("redirect:/configurepassword");
			return mav;
		} else if (action.equals("MyRecipes")) {
			mav.setViewName("redirect:/userpage");
			return mav;
		} else if (action.equals("DeleteRecipes")) {
			mav.setViewName("redirect:/deleterecipes");
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/categoryresults", method = RequestMethod.POST)
	public ModelAndView DisplayRecipesForSelectedCategory(HttpServletRequest request, @RequestParam int categoryID) {
		FetchRecipesDataLayer fetchRecipes = new FetchRecipesDataLayer();
		ModelAndView mav = new ModelAndView();
		ArrayList<IRecipe> recipesForSelectedCategory = new ArrayList<IRecipe>();
		recipesForSelectedCategory = fetchRecipes.getRecipesForCategory(categoryID);
		mav.setViewName("categoryResults");
		mav.addObject("categories", recipesForSelectedCategory);
		return mav;
	}

	@RequestMapping(value = "/cancelHome", method = RequestMethod.POST)
	public ModelAndView redirectToHome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/home");
		return mav;
	}
}