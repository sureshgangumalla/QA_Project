package com.quality.project.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.image.DeleteImageFile;
import com.quality.project.image.Images;
import com.quality.project.image.ImagesToBeAddedForRecipe;
import com.quality.project.image.UploadImageFile;
import com.quality.project.recipeCategory.AddNewCategory;
import com.quality.project.recipeCategory.FetchCategoriesDataLayer;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.user.IUser;

@Controller
public class RecipeController {
	ImagesToBeAddedForRecipe imagesToBeSaved = ImagesToBeAddedForRecipe.getImagesToBeSavedInstance();
	IRecipeFactory recipeFactory = new RecipeFactory();

	@RequestMapping(value = "/addrecipe", method = RequestMethod.GET)
	public ModelAndView showAddOrEditDetails() {
		IRecipe recipe = recipeFactory.makeEmptyRecipe();
		return new ModelAndView("addrecipe", "recipe", recipe);
	}

	@RequestMapping(value = "/addrecipe", method = RequestMethod.POST)
	public String submitAddOrEditDetails(@ModelAttribute("recipe") Recipe recipe, BindingResult result, ModelMap model,
			@RequestParam String action, @RequestParam("newCategory") String newCategory,
			@RequestParam("selectedFile") MultipartFile selectedFile) throws Exception {

		ISessionHandler sessionHandler = SessionHandler.getSession();
		IUser loggedinUser = sessionHandler.getUserIntheSession();
		FetchCategoriesDataLayer categoryList = new FetchCategoriesDataLayer();

		recipe.setUserId(loggedinUser.getUserID());
		recipe.setDefaultcategories(categoryList.getAllCategoriesList());

		model.addAttribute(recipe);

		if (action.equals("Save")) {
			recipe.setImages(imagesToBeSaved.getImages());
			if (0 == recipe.getRecipeId()) {
				HandleRecipe addRecipe = new AddRecipe();
				if (addRecipe.manageRecipe(recipe)) {
					imagesToBeSaved.clear();
					return "redirect:/userpage";
				}
				model.addAttribute("recipeErrors", addRecipe.RecipeErrors());
				return "addrecipe";
			} else {
				HandleRecipe updateRecipe = new UpdateRecipe();
				if (updateRecipe.manageRecipe(recipe)) {
					imagesToBeSaved.clear();
					return "redirect:/userpage";
				}
				model.addAttribute("recipeErrors", updateRecipe.RecipeErrors());
				return "addrecipe";
			}
		}

		else if (action.equals("Cancel")) {
			return "redirect:/userpage";
		}

		else if (action.equals("addNewCatergory")) {
			AddNewCategory addNewCategory = new AddNewCategory();

			boolean isNewCategoryAdded = addNewCategory.addCategory(newCategory);
			if (isNewCategoryAdded) {
				recipe.setDefaultcategories(addNewCategory.getNewDefaultCategories());
			}
			model.addAttribute("categoryErrors", addNewCategory.CategoryErrors());
		}

		else if (action.equals("upload")) {
			UploadImageFile uploadImage = new UploadImageFile();

			boolean isImageUploaded = uploadImage.save(selectedFile);
			if (isImageUploaded) {
				imagesToBeSaved.addImage(uploadImage.getLastSavedImage());
			}
			model.addAttribute("imageErrors", uploadImage.imageErrors());
		}

		else {
			DeleteImageFile deleteImage = new DeleteImageFile();

			Images removedImage = new Images();
			removedImage.setImageName(action);

			boolean isImageDeleted = deleteImage.delete(removedImage);
			if (isImageDeleted) {
				imagesToBeSaved.removeImage(removedImage);
			}
		}
		recipe.setImages(imagesToBeSaved.getImages());
		return "addrecipe";
	}

}
