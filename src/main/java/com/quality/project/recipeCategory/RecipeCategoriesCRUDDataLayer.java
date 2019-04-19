package com.quality.project.recipeCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.recipe.IRecipe;

public class RecipeCategoriesCRUDDataLayer implements IRecipeCategoriesCRUDDataLayer {
	private DBConnector dbConnector;
	private LoggerInstance logs;

	public RecipeCategoriesCRUDDataLayer() {
		logs = LoggerInstance.getInstance();
		dbConnector = DBConnector.getDBConnectorInstance();
	}

	public void insertRecipeCategories(int createdRecipeID, String category) {
		String sp_name = "sp_InsertRecipeCategories";
		Connection connection = dbConnector.getDBConnection();

		try {
			CallableStatement stmt;
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + sp_name + "(?,?)}");
				stmt.setString("categoryName", category);
				stmt.setInt("recipeID", createdRecipeID);
				stmt.executeUpdate();
				stmt.close();

			}
			logs.log(Logger.INFO, "Categories of " + createdRecipeID + " added Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed to insert Categories of RecipeID:" + createdRecipeID + "");

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
	}

	public boolean deleteRecipeCategories(IRecipe recipe) {
		String sp_name = "sp_DeleteRecipeCategories";
		Connection connection = dbConnector.getDBConnection();

		try {
			CallableStatement stmt;
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + sp_name + "(?)}");
				stmt.setInt("recipeID", recipe.getRecipeId());
				stmt.executeUpdate();
				stmt.close();
			}
			logs.log(Logger.INFO, "Categories of " + recipe.getRecipeId() + " deleted Successfully!");
			return true;
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed to delete Categories of RecipeID:" + recipe.getRecipeId() + "");
			return false;

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
	}

	public ArrayList<String> getCategoriesForRecipe(int id) {

		ArrayList<String> categoryList = new ArrayList<String>();

		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement st = null;
		String source = "sp_fetchCategoriesForRecipe";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt(1, id);
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				String category = resultSet.getString("category_name");
				categoryList.add(category);
			}
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return categoryList;
	}

}
