package com.quality.project.recipeCategory;

import java.util.ArrayList;
import java.util.Map;

public class AddNewCategory {

	CategoryValidator validator;
	CategoryDataLayerTemplate categoryDataLayerTemplate;
	FetchCategoriesDataLayer fetchCategories;
	ArrayList<String> defaultcategories;

	public AddNewCategory() {
		validator = new CategoryValidator();
		categoryDataLayerTemplate = new CategoryDataLayerTemplate();
		defaultcategories = new ArrayList<String>();
		fetchCategories = new FetchCategoriesDataLayer();
	}

	public boolean addCategory(String newCategory) throws Exception {
		// replace with category
		ICategory newCategoryObj = new Category();
		newCategoryObj.setCategoryName(newCategory);

		if (validator.isValidCategory(newCategoryObj)) {
			categoryDataLayerTemplate.add(newCategoryObj);
			return true;
		}
		return false;
	}

	public ArrayList<String> getNewDefaultCategories() throws Exception {
		defaultcategories = fetchCategories.getAllCategoriesList();
		return defaultcategories;
	}

	public Map<String, String> CategoryErrors() {
		return validator.getErrors();
	}
}
