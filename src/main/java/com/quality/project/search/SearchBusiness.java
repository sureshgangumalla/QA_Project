package com.quality.project.search;
import java.util.ArrayList;
import java.util.List;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.templates.ICRUDTemplate;

public class SearchBusiness  implements ISearchBuilder, ISearchSuggestion{

	private String recipeName="";
	private int recipeId;
	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	LoggerInstance logs;
	private boolean canLogExceptions;
	int totalNumberOfRecipes = 0;
	public SearchBusiness() {
		try {
			logs = LoggerInstance.getInstance();
			canLogExceptions = true;
		}catch (Exception e) {
			canLogExceptions = false;
		}
	}	
	@Override
	public void buildRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	@Override
	public void buildRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}	
	@Override
	public String getBuildRecipeName() {
		return this.recipeName;
	}
	@Override
	public int getBuildRecipeId() {
		return recipeId;
	}	
	@Override
	public List<ISearch> getSearchSuggestions(String inputValue) {
		try {
			if(isInputValid(inputValue)) {
				int userId = getCurrentUserId();
				List<ISearch> stringSuggestions = new ArrayList<ISearch>();
				SearchSuggestionDataLayerTemplate suggestions = new SearchSuggestionDataLayerTemplate();
				List<ISearch> returnedResults = suggestions.Select(inputValue,userId);
				for (int i =0;i<returnedResults.size();i++) {
					ISearch searchObj = new Search();
					searchObj.setSerachSuggestion(returnedResults.get(i).getSearchSuggestion());
					stringSuggestions.add(searchObj);
				}
				return stringSuggestions;
			}			
		}catch(Exception e) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
		}
		return null;
	}	
	public boolean isInputValid(String inputValue) {
		if(inputValue.isEmpty() || inputValue.equals("")) {
			return false;
		}
		return true;
	}
	@Override
	public void insertSearchSuggestion(String inputValue){
		try {
			if(isInputValid(inputValue)) {
				int userId = getCurrentUserId();		
				ISearch searchObj = new Search();
				searchObj.setSerachSuggestion(inputValue);
				searchObj.setUserId(userId);
				ICRUDTemplate<ISearch> suggestions = new SearchSuggestionDataLayerTemplate();
				suggestions.add(searchObj);
			}
		}catch(Exception e) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
		}
	}	
	public List<String> getAllCusines() {
		try {
			SearchCuisineDataLayer cuisineListObj = new SearchCuisineDataLayer();
			return cuisineListObj.getCuisineList();
		}catch(Exception e) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
		}
		return null;
	}	
	public List<ISearchBuilder> getAdvanceSearchResults(String search, String cuisine, String category){
		List<ISearchBuilder> buildRecipeList = new ArrayList<ISearchBuilder>();
		String source = "sp_fetchAdvanceSearchResults";
		try {			
			IAdvanceSearch advanceSearchLayer = new AdvanceSearchDataLayer();
			buildRecipeList = advanceSearchLayer.executeAdvanceSearch(source,search,cuisine,category);
		} catch (Exception e) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
		}
		return buildRecipeList; 
	}
	public int getTotalNumberOfPageCount(String searchText) {
		String source = "sp_fetchCountForRecipes";
		String totalNumberOfResultsSource ="sp_fetchCountForResults";
		int totalCountOfRecipesAgainstSearchTerm = 0;
		try {			
			IAdvanceSearch searchResults = new AdvanceSearchDataLayer();
			totalCountOfRecipesAgainstSearchTerm = searchResults.getSearchResultsPageCount(source,searchText);			
			totalNumberOfRecipes = searchResults.totalNumberOfRecipes(totalNumberOfResultsSource);
		} catch (Exception e) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, e.getMessage());
			e.printStackTrace();
		}		
		return totalCountOfRecipesAgainstSearchTerm/totalNumberOfRecipes;
	}			
	public int getTotalResultCount() {
		return totalNumberOfRecipes;
	}		
	public List<ISearchBuilder> searchResults(String search, int pageNumber)throws Exception{
		try { 
			if(search == null) {
				search = null;
			}
			int offsetValue = (pageNumber-1)*10;
			List<ISearchBuilder> recipesInList = new ArrayList<ISearchBuilder>();
			String source = "sp_fetchsearchResults";
			try {			
				IAdvanceSearch searchResults = new AdvanceSearchDataLayer();
				recipesInList = searchResults.getAllResults(source,search,9,offsetValue);
			} catch (Exception e) {
				if(canLogExceptions)
					logs.log(Logger.ERROR, e.getMessage());
				e.printStackTrace();
			}
			return recipesInList;
		}catch(Exception ex) {
			if(canLogExceptions)
				logs.log(Logger.ERROR, ex.getMessage());
			throw ex;
		}
	}		
	public int getCurrentUserId() {
			ISessionHandler sessionHandler = SessionHandler.getSession();
			if(sessionHandler.isSessionStarted()) {
				return sessionHandler.getUserIntheSession().getUserID();
			}
			return 0;		
	}
}
