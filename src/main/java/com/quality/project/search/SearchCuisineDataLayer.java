package com.quality.project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class SearchCuisineDataLayer{	
	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	LoggerInstance logs = LoggerInstance.getInstance();	
	public List<String> getCuisineList() throws Exception{
		List<String> cusineList = new ArrayList<String>();
		String source = "sp_fetchCuisine";	
		try {
			Connection connection = dbConnector.getDBConnection();
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "()}");
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while(resultSet.next()) {
						cusineList.add(resultSet.getString("recipe_cuisine"));
					}
				}
			}
			connection.close();
		} catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
		}
		return cusineList;
	}
}
