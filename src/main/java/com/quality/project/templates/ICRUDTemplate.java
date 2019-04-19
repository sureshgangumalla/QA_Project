package com.quality.project.templates;

import java.util.List;

import com.quality.project.recipeCategory.ICategory;

public interface ICRUDTemplate<T> {
	public int add(T Obj) throws Exception;
	public int update(T Obj) throws Exception;
	public int remove(T Obj) throws Exception; 
	public List<T> Select() throws Exception;	
}
