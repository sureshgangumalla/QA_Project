package com.quality.project.JUnit.Search;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.quality.project.search.Search;

public class SearchTest {
	Search search;
	private ISearchMockFactory searchMock;
	
	@Before
	public void setUp() {
		searchMock = new SearchMockFactory();
		search = new Search();
	}	
	@Test
	public void checkCategoryObjIsNotEmpty() throws Exception { 	
		assertEquals(true, search.isSearchObjNull(searchMock.getSearchResult(0)));
	}	
	@Test
	public void checkCategoryObjIsCheckingForEmpty() throws Exception { 		
		assertEquals(true, search.isSearchObjNull(searchMock.getSearchResult(1)));
	}	
	@Test
	public void checkIfCategoryIsNullIsWorkingForValidString() throws Exception { 		
		assertEquals(true, search.checkIfSearchIsNull(searchMock.getSearchResult(0).getSearchSuggestion()));
	}	
	@Test
	public void checkIfCategoryIsNullIsWorkingForInvalidString() throws Exception { 		
		assertEquals(false, search.checkIfSearchIsNull(searchMock.getSearchResult(1).getSearchSuggestion()));
	}
}
