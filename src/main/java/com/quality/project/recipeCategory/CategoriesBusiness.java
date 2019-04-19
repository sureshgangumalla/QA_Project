package com.quality.project.recipeCategory;

import java.util.List;

import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.templates.ICRUDTemplate;

public class CategoriesBusiness {
	ICRUDTemplate<ICategory> categoryDataLayer = new CategoryDataLayerTemplate();
	LoggerInstance logs;
	private boolean canLogExceptions; 
	Category category = new Category();
	public CategoriesBusiness() {
		try {
			logs = LoggerInstance.getInstance();
			canLogExceptions = true;
		}catch (Exception e) {
			canLogExceptions = false;
		}
	}
	public int addCategory(ICategory Obj)throws Exception {
		try {
			return categoryDataLayer.add(Obj); 
		}catch(Exception e){
			e.printStackTrace();
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
			return -1;
		}
	}
	public int updateCategory(ICategory Obj) throws Exception{
		return categoryDataLayer.update(Obj);
	}
	public int removeCategory(ICategory Obj) throws Exception {
		return categoryDataLayer.remove(Obj);
	}
	public List<ICategory> getAllCategories() throws Exception{
		return categoryDataLayer.Select();
	}
	
}
