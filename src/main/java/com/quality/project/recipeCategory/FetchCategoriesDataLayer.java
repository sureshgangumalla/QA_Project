package com.quality.project.recipeCategory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class FetchCategoriesDataLayer {

	private DBConnector dbConnector;
	LoggerInstance logs;

	public FetchCategoriesDataLayer() {
		logs = LoggerInstance.getInstance();
		dbConnector = DBConnector.getDBConnectorInstance();
	}

	public ArrayList<String> getAllCategoriesList() {

		ArrayList<String> allCategoriesList = new ArrayList<String>();

		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement st = null;
		String source = "sp_getAllCategoriesList";
		try {

			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "}");
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				String category = resultSet.getString("category_name");
				allCategoriesList.add(category);
			}
			logs.log(Logger.INFO, "All Categories retrieved Successfully!");
		} catch (Exception e) {
			logs.log(Logger.INFO, "Failed to retrive All Categories");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return allCategoriesList;

	}

	public ArrayList<ICategory> getCategoriesWithRecipes() {
		ArrayList<ICategory> categoriesWithRecipesList = new ArrayList<ICategory>();

		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement st = null;
		String source = "sp_getCategoriesWithRecipes";
		try {

			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "}");
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				ICategory category = new Category();
				category.setCategoryName(resultSet.getString("category_name"));
				category.setCategoryId(resultSet.getInt("category_id"));
				categoriesWithRecipesList.add(category);
			}
			logs.log(Logger.INFO, "All CategoriesWithRecipes retrieved Successfully!");
			return categoriesWithRecipesList;

		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed to retrive All Categories");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return categoriesWithRecipesList;
	}
}
