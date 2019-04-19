package com.quality.project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.recipeCategory.Category;
import com.quality.project.recipeCategory.ICategory;
import com.quality.project.templates.ICRUDTemplate;

public class SearchSuggestionDataLayerTemplate implements ICRUDTemplate<ISearch>{

	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	LoggerInstance logs = LoggerInstance.getInstance();
	@Override
	public int add(ISearch Obj) throws Exception{
		String source ="sp_addSearchSuggestion";
		try {
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?,?)}");
				stmt.setInt(1, Obj.getUserId());
				stmt.setString(2, Obj.getSearchSuggestion());
				count = stmt.executeUpdate();
			}	
			connection.close();
			return count;
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
			throw e;
		}
	}

	@Override
	public int update(ISearch Obj) throws Exception{
		try {
			String source = "sp_updateCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getSearchSuggestion());
				stmt.setInt(2, Obj.getUserId());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		}catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
			throw e;
		}
	}

	@Override
	public int remove(ISearch Obj)throws Exception {
		try {
			String source = "sp_removeCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getSearchSuggestion());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		}catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
			throw e;
		}	
	}

	@Override
	public List<ISearch> Select() throws Exception{
		String source ="sp_fetchSearchSuggestion";
		List<ISearch> searchSuggestionList = new ArrayList<ISearch>();
		Connection connection = dbConnector.getDBConnection();
		try {	
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "()}");
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						ISearch searchSuggestion = new Search();
						searchSuggestion.setUserId(resultSet.getInt("user_id"));
						searchSuggestion.setSerachSuggestion(resultSet.getString("search"));										
						searchSuggestionList.add(searchSuggestion);
					}
				}
			}
			connection.close();			
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		return searchSuggestionList;
	}

	public List<ISearch> Select(String inputValue, int userId) {		
		String source ="sp_fetchSearchSuggestionByKeyword";
		List<ISearch> searchSuggestionList = new ArrayList<ISearch>();
		try {			
			ResultSet resultSet = null;
			Connection connection = dbConnector.getDBConnection();
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?,?)}");
				stmt.setString(1, inputValue);
				stmt.setInt(2, userId);
				Boolean hasResuts = stmt.execute();				
				if (hasResuts) {
					resultSet = stmt.getResultSet();
				}
			while(resultSet.next()) {
				ISearch searchSuggestion = new Search();
				searchSuggestion.setUserId(resultSet.getInt("user_id"));
				searchSuggestion.setSerachSuggestion(resultSet.getString("search"));								
				searchSuggestionList.add(searchSuggestion);
			}} 
			connection.close();
		}catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		return searchSuggestionList;
	}

}
