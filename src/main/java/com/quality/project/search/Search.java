package com.quality.project.search;

public class Search implements ISearch{
	public String searchSuggestions;
	public int userId;
	@Override
	public String getSearchSuggestion() {
		return this.searchSuggestions;
	}
	@Override
	public void setSerachSuggestion(String searchSuggestion) {
		this.searchSuggestions = searchSuggestion;		
	}
	@Override
	public int getUserId() {
		return this.userId;
	}
	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean checkIfSearchIsNull(String passedCategoryName) {
		if (passedCategoryName.isEmpty() || passedCategoryName == null) {
			return true;
		} else {
			return false;
		}
	}	
	public boolean isSearchObjNull(ISearch obj) {
		if(obj.getUserId()>0 && obj.getSearchSuggestion()!="") {
			return false;
		}else {
			return true;
		}
	}
}
