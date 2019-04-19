package com.quality.project.search;

import java.util.List;

public interface ISearchSuggestion {
	public List<ISearch> getSearchSuggestions(String inputValue);
	public void insertSearchSuggestion(String inputValue);
}
