package com.quality.project.JUnit.CategoryValidatorTest;

import java.util.HashMap;
import java.util.Map;

import com.quality.project.recipeCategory.Category;
import com.quality.project.recipeCategory.ICategory;

public class CategoryMockFactory implements ICategoryMockFactory {
	private Map<String, ICategory> categories;

	public CategoryMockFactory() {
		this.categories = new HashMap<String, ICategory>();
		this.createCategories();
	}

	public void createCategories() {
		ICategory emptyCategory = new Category();
		emptyCategory.setCategoryName("");
		categories.put("emptyCategory", emptyCategory);

		ICategory validCategory = new Category();
		validCategory.setCategoryName("TestCategory");
		categories.put("validCategory", validCategory);
	}

	public ICategory getCategories(String type) {
		return categories.get(type);
	}
}
