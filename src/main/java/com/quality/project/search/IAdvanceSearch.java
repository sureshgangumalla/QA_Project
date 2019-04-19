package com.quality.project.search;

import java.util.List;

public interface IAdvanceSearch {
	public int getSearchResultsPageCount(String source, String searchText) throws Exception ;
	public int totalNumberOfRecipes(String source) throws Exception ;
	public List<ISearchBuilder> getAllResults(String source, String input, int pageNumber, int numberOfResults) throws Exception ;
	public List<ISearchBuilder> executeAdvanceSearch(String source, String input, String cuisine, String category) throws Exception ;
}
