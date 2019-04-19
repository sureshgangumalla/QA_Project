package com.quality.project.JUnit.Category;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.quality.project.recipeCategory.Category;
import com.quality.project.recipeCategory.CategoriesBusiness;

public class CategoriesTest {

	Category categories;
	private ICategoriesMockFactory categoryMock;
	
	@Before
	public void setUp() {
		categoryMock = new CategoriesMockFactory();
		categories = new Category();
	}	
	@Test
	public void checkCategoryObjIsNotEmpty() throws Exception { 	
		assertEquals(false, categories.isCategoryObjNull(categoryMock.getCategories(0)));
	}	
	@Test
	public void checkCategoryObjIsCheckingForEmpty() throws Exception { 		
		assertEquals(true, categories.isCategoryObjNull(categoryMock.getCategories(1)));
	}	
	@Test
	public void checkIfCategoryIsNullIsWorkingForValidString() throws Exception { 		
		assertEquals(false, categories.checkIfCategoryIsNull(categoryMock.getCategories(0).getCategoryName()));
	}	
	@Test
	public void checkIfCategoryIsNullIsWorkingForInvalidString() throws Exception { 		
		assertEquals(true, categories.checkIfCategoryIsNull(categoryMock.getCategories(1).getCategoryName()));
	}
}
