package com.quality.project.JUnit.Category;

import java.util.HashMap;

import com.quality.project.recipeCategory.Category;
import com.quality.project.recipeCategory.ICategory;

public class CategoriesMockFactory implements ICategoriesMockFactory{

	private static HashMap<Integer, ICategory> categoryHashMap;
	public CategoriesMockFactory() {
		categoryHashMap = new HashMap<Integer, ICategory>();
		loadCategories();
	}
	private void loadCategories() {
		ICategory categoryMockObject1 = new Category();
		categoryMockObject1.setCategoryId(1);
		categoryMockObject1.setCategoryName("test Category");		
		categoryHashMap.put(0, categoryMockObject1);
		
		ICategory categoryMockObject2 = new Category();
		categoryMockObject2.setCategoryId(2);
		categoryMockObject2.setCategoryName("");
		categoryHashMap.put(1, categoryMockObject2);
		
		ICategory categoryMockObject3 = new Category();
		categoryMockObject3.setCategoryId(3);
		categoryMockObject3.setCategoryName(null);
		categoryHashMap.put(2, categoryMockObject3);
		
	}
	@Override
	public ICategory getCategories(int id) {
		return categoryHashMap.get(id);
	}
}
