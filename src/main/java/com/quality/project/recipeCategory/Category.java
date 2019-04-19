package com.quality.project.recipeCategory;

public class Category implements ICategory {
	public int categoryId;
	public String categoryName;

	@Override
	public int getCategoryId() {
		return categoryId;
	}
	@Override
	public void setCategoryId(int categoryId) {	
		this.categoryId = categoryId;		 
	}
	@Override
	public String getCategoryName() {
		return categoryName;
	}
	@Override
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;		
	}
	public boolean checkIfCategoryIsNull(String passedCategoryName) {
		if (passedCategoryName.isEmpty() || passedCategoryName == null) {
			return true;
		} else {
			return false;
		}
	}	
	public boolean isCategoryObjNull(ICategory obj) {
		if(obj.getCategoryId()>0 && obj.getCategoryName()!="") {
			return false;
		}else {
			return true;
		}
	}
}
