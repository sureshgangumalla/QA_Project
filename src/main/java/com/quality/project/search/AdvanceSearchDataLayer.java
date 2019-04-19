package com.quality.project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class AdvanceSearchDataLayer implements IAdvanceSearch{
	
	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	LoggerInstance logs = LoggerInstance.getInstance();	
	
	@Override
	public List<ISearchBuilder> executeAdvanceSearch(String source, String input, String cuisine, String category) throws Exception {		
		Connection connection = dbConnector.getDBConnection();
		List<ISearchBuilder> buildRecipeList = new ArrayList<ISearchBuilder>();
		try {
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?,?,?)}");
				stmt.setString(1, input);
				stmt.setString(2, cuisine);
				stmt.setString(3, category);
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while(resultSet.next()) {
						ISearchBuilder searchObj = new SearchBusiness();
						searchObj.buildRecipeId(resultSet.getInt("recipe_id"));
						searchObj.buildRecipeName(resultSet.getString("recipe_name"));
						buildRecipeList.add(searchObj);
					}
				}
				connection.close();
			}			
		}catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
		}
		return buildRecipeList;
	}
	
	@Override
	public int getSearchResultsPageCount(String source, String searchText) throws Exception {		
		int totalCountOfRecipesAgainstSearchTerm = 0;
		try {
			Connection connection = dbConnector.getDBConnection();
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, searchText);
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while(resultSet.next()) {
						totalCountOfRecipesAgainstSearchTerm = resultSet.getInt("count");
					}
				}
				connection.close();
			}			
		}catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
		}
		return totalCountOfRecipesAgainstSearchTerm;
	}
	
	@Override
	public int totalNumberOfRecipes(String source) throws Exception {
		int totalNumberOfRecipes = 0;
		try {
			Connection connection = dbConnector.getDBConnection();
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "()}");
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while(resultSet.next()) {
						totalNumberOfRecipes = resultSet.getInt("search_column");
					}
				}
			}
			connection.close();
		}catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
		}
		return totalNumberOfRecipes;
	}
	
	@Override
	public List<ISearchBuilder> getAllResults(String source, String input, int pageNumber, int numberOfResults) throws Exception {
		Connection connection = dbConnector.getDBConnection();
		List<ISearchBuilder> resultsRecipes = new ArrayList<ISearchBuilder>();		
		try {
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?,?,?)}");
				stmt.setString(1, input);
				stmt.setInt(2, pageNumber);
				stmt.setInt(3, numberOfResults);
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while(resultSet.next()) {
						ISearchBuilder searchObj = new SearchBusiness();
						searchObj.buildRecipeId(resultSet.getInt("recipe_id"));
						searchObj.buildRecipeName(resultSet.getString("recipe_name"));
						resultsRecipes.add(searchObj);
					}
				}
			}
			connection.close();
		}catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
		}
		return resultsRecipes;
	}

}
