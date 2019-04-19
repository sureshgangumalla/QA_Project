package com.quality.project.recipeCategory;

import java.util.Map;

public interface ICategoryValidator{
	
	public Map<String, String> getErrors();
	public boolean isValidCategory(ICategory category);
}
