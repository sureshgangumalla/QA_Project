package com.quality.project.JUnit.CategoryValidatorTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.recipeCategory.Category;
import com.quality.project.recipeCategory.CategoryValidator;
import com.quality.project.recipeCategory.ICategory;

public class CategoryValidatorTest {
	private ICategoryMockFactory categoryMock;
	private CategoryValidator validator;

	@Before
	public void setUp() {
		categoryMock = new CategoryMockFactory();
		validator = new CategoryValidator();
	}

	@Test
	public void emptyNewCatgeoryName() {
		ICategory category = new Category();
		category = categoryMock.getCategories("emptyCategory");
		assertTrue(false == validator.isValidCategory(category));
	}

	@Test
	public void validNewCategoryName() {
		ICategory category = new Category();
		category = categoryMock.getCategories("validCategory");
		assertTrue(true == validator.isValidCategory(category));
	}
}
