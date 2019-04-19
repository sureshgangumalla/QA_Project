package com.quality.project.recipeCategory;

import java.util.HashMap;
import java.util.Map;

public class CategoryValidator implements ICategoryValidator {
	private Map<String, String> categoryErrors;

	public CategoryValidator() {
		this.categoryErrors = new HashMap<String, String>();
	}

	@Override
	public boolean isValidCategory(ICategory category) {

		boolean validity = true;
		if (category.getCategoryName().isEmpty()) {
			validity = false;
			categoryErrors.put("categoryname", "Please provide category name");
		}
		return validity;
	}

	@Override
	public Map<String, String> getErrors() {
		return categoryErrors;
	}

}
