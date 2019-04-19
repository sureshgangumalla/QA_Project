package com.quality.project.JUnit.Search;

import java.util.HashMap;
import java.util.Map;

import com.quality.project.recipeCategory.ICategory;
import com.quality.project.search.ISearch;
import com.quality.project.search.Search;

public class SearchMockFactory implements ISearchMockFactory {
	private Map<Integer, ISearch> search;

	public SearchMockFactory() {
		search = new HashMap<Integer, ISearch>();
		createSearch();
	}

	public void createSearch() {
		ISearch emptySearch = new Search();
		emptySearch.setSerachSuggestion("");
		emptySearch.setUserId(0);
		search.put(0, emptySearch);

		ISearch validSearch = new Search();
		validSearch.setSerachSuggestion("Test Search Suggestion");
		validSearch.setUserId(0);
		search.put(1, validSearch);
	}

	@Override
	public ISearch getSearchResult(int id) {
		return search.get(id);
	}
}
