package com.quality.project.JUnit.CategoryValidatorTest;

import com.quality.project.recipeCategory.ICategory;

public interface ICategoryMockFactory {
	public void createCategories();
	public ICategory getCategories(String type);
}
