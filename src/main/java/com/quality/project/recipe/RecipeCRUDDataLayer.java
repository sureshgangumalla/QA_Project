package com.quality.project.recipe;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quality.project.Admin.AdminApprove;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.recipeCategory.RecipeCategoriesCRUDDataLayer;
import com.quality.project.user.IUser;

public class RecipeCRUDDataLayer {

	private LoggerInstance logs;
	private DBConnector dbConnector;
	private AdminApprove adminApprove;
	private RecipeCategoriesCRUDDataLayer recipeCategoriesCRUD;

	public RecipeCRUDDataLayer() {
		logs = LoggerInstance.getInstance();
		dbConnector = DBConnector.getDBConnectorInstance();
		adminApprove = new AdminApprove();
		recipeCategoriesCRUD = new RecipeCategoriesCRUDDataLayer();
	}

	public IRecipe getRecipe(int id) {
		IRecipe recipe = null;
		ResultSet resultSet = null;
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_fetchRecipe";
		IUser user = null;
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt(1, id);

				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
				recipe = new Recipe();

				while (resultSet.next()) {
					recipe.setRecipeId(resultSet.getInt("recipe_id"));
					recipe.setCuisine(resultSet.getString("recipe_cuisine"));
					recipe.setDescription(resultSet.getString("recipe_description"));
					recipe.setSteps(resultSet.getString("recipe_steps"));
					recipe.setRecipeName(resultSet.getString("recipe_name"));
					recipe.setAverageRating(resultSet.getFloat("avg_rating"));
					recipe.setStatus(resultSet.getString("status"));
					recipe.setUserId(resultSet.getInt("user_id"));
					recipe.setCategories(recipeCategoriesCRUD.getCategoriesForRecipe(id));
					recipe.setIngredients(resultSet.getString("recipe_ingredients"));
					user = adminApprove.getPersonDetails(resultSet.getInt("user_id"));
					recipe.setUploaderName(user.getUsername());
				}

				logs.log(Logger.INFO,
						"Recipe Details of " + resultSet.getInt("recipe_name") + " has retrieved Successfully!");
			}

		} catch (SQLException e) {
			logs.log(Logger.ERROR, e.getMessage());
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}

		return recipe;
	}

	public int addNewRecipe(IRecipe recipe) {
		int createdRecipeID = 0;
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_addNewRecipe";
		try {

			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?,?,?,?,?,?)}");
				st.setString("getRecipeName", recipe.getRecipeName());
				st.setString("getCuisine", recipe.getCuisine());
				st.setString("getDescription", recipe.getDescription());
				st.setString("getIngredients", recipe.getIngredients());
				st.setString("getSteps", recipe.getSteps());
				st.setInt("getUserId", recipe.getUserId());
				st.execute();

			}
			createdRecipeID = getRecipeID(recipe);
			logs.log(Logger.INFO, "Recipe details added " + recipe.getRecipeName() + "Successfully!");
			return createdRecipeID;
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Inserting receipe details");
			return 0;
		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
	}

	public boolean updateRecipe(IRecipe recipe) {

		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_updateRecipe";
		try {

			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?,?,?,?,?,?)}");
				st.setString("getRecipeName", recipe.getRecipeName());
				st.setString("getCuisine", recipe.getCuisine());
				st.setString("getDescription", recipe.getDescription());
				st.setString("getIngredients", recipe.getIngredients());
				st.setString("getSteps", recipe.getSteps());
				st.setInt("getRecipeId", recipe.getRecipeId());
				st.execute();

			}
			logs.log(Logger.INFO, "Recipe details updated " + recipe.getRecipeName() + "Successfully!");
			return true;
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in updating receipe details");

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return false;

	}

	public boolean DeleteRecipeDetails(IRecipe recipe) {
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_DeleteRecipeDetails";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt("getRecipeId", recipe.getRecipeId());
				st.execute();
			}
			logs.log(Logger.INFO, "Recipe " + recipe.getRecipeName() + " Deleted Successfully!");
			return true;

		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Deleting receipe Images");
			return false;

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
	}

	public int getRecipeID(IRecipe recipe) {
		int recipeID = 0;
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getRecipeID";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setString("getRecipeName", recipe.getRecipeName());

				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}

			while (resultSet.next()) {
				recipeID = resultSet.getInt("recipe_id");
			}
			logs.log(Logger.INFO, "Recipe ID of " + recipe.getRecipeName() + " retrived Successfully!");
			return recipeID;

		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in getting recipe ID  of " + recipe.getRecipeName() + "");
			return recipeID;
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}

	}

}