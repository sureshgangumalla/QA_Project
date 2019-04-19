package com.quality.project.recipe;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.home.IHome;
import com.quality.project.image.IImages;
import com.quality.project.image.ImageCRUDDataLayer;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.ratings.IRatings;
import com.quality.project.ratings.Ratings;
import com.quality.project.user.IUser;

public class FetchRecipesDataLayer implements IHome {
	private LoggerInstance logs;
	private DBConnector dbConnector;

	public FetchRecipesDataLayer() {
		logs = LoggerInstance.getInstance();
		dbConnector = DBConnector.getDBConnectorInstance();
	}

	public List<IRecipe> getAllRecipes() {
		ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getAllRecipes";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "}");
				Boolean hasResults = st.execute();
				if (hasResults) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				IRecipe recipe = new Recipe();
				recipe.setRecipeId(resultSet.getInt("recipe_id"));
				recipe.setRecipeName(resultSet.getString("recipe_name"));
				recipes.add(recipe);
			}
			logs.log(Logger.INFO, "Approved Recipes retrieved Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Fetching Approved Recipes");
		} finally {
			try {
				resultSet.close();
				st.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return recipes;
	}

	public ArrayList<IRecipe> getRecipesForCategory(int categoryId) {
		ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getRecipesForCategory";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt("categoryId", categoryId);
				Boolean hasResults = st.execute();
				if (hasResults) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				IRecipe recipe = new Recipe();
				recipe.setRecipeId(resultSet.getInt("recipe_id"));
				recipe.setRecipeName(resultSet.getString("recipe_name"));
				recipes.add(recipe);
			}
			logs.log(Logger.INFO, "Recipe Categrories of " + categoryId + " has retrieved Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Fetching Recipes for categories ");
		} finally {
			try {
				resultSet.close();
				st.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}

		}
		return recipes;
	}

	public IRecipe getRecipeWithSameRecipeNameAndDifferentRecipeID(IRecipe recipeToBeValidated) {
		IRecipe exsistingRecipe = new Recipe();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getRecipeWithSameRecipeNameAndDifferentRecipeID";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?,?)}");
				st.setString("getRecipeName", recipeToBeValidated.getRecipeName());
				st.setInt("getRecipeId", recipeToBeValidated.getRecipeId());
				Boolean hasResults = st.execute();
				if (hasResults) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				exsistingRecipe.setRecipeId(resultSet.getInt("recipe_id"));
				exsistingRecipe.setRecipeName(resultSet.getString("recipe_name"));
				logs.log(Logger.INFO,
						"Retrived Exsisting recipe " + exsistingRecipe.getRecipeName() + " Successfully!");
			}
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Fetching RecipeWithSameRecipeNameAndDifferentRecipeID");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return exsistingRecipe;
	}

	public List<IRecipe> getRecipesByUser(IUser user) {

		List<IRecipe> recipesByUser = new ArrayList<IRecipe>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getRecipesByUser";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt("getUserID", user.getUserID());
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				IRecipe recipe = new Recipe();
				recipe.setRecipeId(resultSet.getInt("recipe_id"));
				recipe.setRecipeName(resultSet.getString("recipe_name"));
				recipe.setLastUpdateTime(resultSet.getTimestamp("recipe_lastupdate_time"));
				recipe.setStatus(resultSet.getString("status"));
				recipesByUser.add(recipe);
			}
			logs.log(Logger.INFO, "Retrived recipes " + user.getUsername() + "Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Fetching to be user recipes");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return recipesByUser;
	}

	public List<IRecipe> getRecipesToBeApproved() {

		List<IRecipe> receipesToBeApproved = new ArrayList<IRecipe>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		ResultSet resultSet = null;
		String source = "sp_getRecipesToBeApproved";
		try {
			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "}");

				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}
			while (resultSet.next()) {
				IRecipe recipe = new Recipe();
				recipe.setRecipeId(resultSet.getInt("recipe_id"));
				recipe.setRecipeName(resultSet.getString("recipe_name"));
				recipe.setLastUpdateTime(resultSet.getTimestamp("recipe_lastupdate_time"));
				receipesToBeApproved.add(recipe);
			}
			logs.log(Logger.INFO, "Recipes to be Approved Retrived Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Error occured in Fetching to be approved recipes");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
		return receipesToBeApproved;
	}

	public List<IRatings> mostPopularProducts() {
		ImageCRUDDataLayer it = new ImageCRUDDataLayer();
		String source = "sp_newArrivals";
		List<IRatings> ratingsList = new ArrayList<IRatings>();
		ArrayList<String> imageLocations = new ArrayList<String>();
		imageLocations.add("assets/images/default.png");
		try {
			Connection connection = dbConnector.getDBConnection();
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "()}");
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						IRatings ratings = new Ratings();
						ratings.setRecipeId(resultSet.getInt("recipe_id"));
						ArrayList<IImages> images = it.getImagesForRecipes(resultSet.getInt("recipe_id"));
						for (IImages image : images) {
							imageLocations.clear();
							imageLocations.add(image.getImageLocation());
						}
						ratings.setImageLocation(imageLocations.get(0));
						ratings.setRecipeName(resultSet.getString("recipe_name"));
						ratingsList.add(ratings);
					}
				}
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("error occured in  fetching popular recipes");
			e.printStackTrace();
		}

		return ratingsList;
	}
}
