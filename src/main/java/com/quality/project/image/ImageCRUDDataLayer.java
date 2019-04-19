package com.quality.project.image;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class ImageCRUDDataLayer implements IImageCRUDDataLayer {
	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	LoggerInstance logs;

	public ImageCRUDDataLayer() {
		logs = LoggerInstance.getInstance();
	}

	public boolean addImageForRecipe(int createdRecipeID, IImages image) {
		String sp_name = "sp_addImageForRecipe";
		Connection connection = dbConnector.getDBConnection();

		try {
			CallableStatement stmt;
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + sp_name + "(?,?,?)}");
				stmt.setInt("createdRecipeID", createdRecipeID);
				stmt.setString("getImageName", image.getImageName());
				stmt.setString("getImageLocation", image.getImageLocation());
				stmt.executeUpdate();
				stmt.close();
			}
			logs.log(Logger.INFO, "Image of " + createdRecipeID + " added Successfully!");
			return true;
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed to insert Image of RecipeID:" + createdRecipeID + "");
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}
	}

	public ArrayList<IImages> getImagesForRecipes(int recipeId) {
		ArrayList<IImages> imagesList = new ArrayList<IImages>();
		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement st = null;
		String source = "sp_fetchImagesForRecipes";
		try {

			if (connection != null) {
				st = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				st.setInt(1, recipeId);
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
			}

			while (resultSet.next()) {
				IImages images = new Images();
				images.setImageName(resultSet.getString("image_name"));
				images.setImageLocation(resultSet.getString("image_path"));
				images.setImageNameAndLocation(images.getImageLocation() + "/" + images.getImageName());
				imagesList.add(images);
			}

			logs.log(Logger.INFO, "Image of " + recipeId + " has retrieved Successfully!");
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed 233to get Images" + recipeId + "");
		} finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}

		return imagesList;
	}

	public boolean DeleteImagesForRecipe(int recipeID) {
		Connection connection = dbConnector.getDBConnection();
		String sp_name = "sp_DeleteImagesForRecipe";

		try {
			CallableStatement stmt;
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + sp_name + "(?)}");
				stmt.setInt("createdRecipeID", recipeID);
				stmt.executeUpdate();
				stmt.close();
			}
			logs.log(Logger.INFO, "Recipe " + recipeID + " Deleted Successfully!");
			return true;
		} catch (Exception e) {
			logs.log(Logger.ERROR, "Failed to Delete Recipe" + recipeID + "");
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, "Failed to close DBConnection");
			}
		}

	}

}
