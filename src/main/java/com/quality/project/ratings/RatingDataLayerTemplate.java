package com.quality.project.ratings;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.recipe.IRecipe;
import com.quality.project.templates.ICRUDTemplate;

public class RatingDataLayerTemplate implements ICRUDTemplate<IRatings>{
	private LoggerInstance logs;
	private DBConnector dbConnector;
	private String source;

	public RatingDataLayerTemplate() {
		dbConnector = DBConnector.getDBConnectorInstance();
		logs = LoggerInstance.getInstance();
	}

	public boolean DeleteRecipeRatings(IRecipe recipe) {
		Connection connection = dbConnector.getDBConnection();
		PreparedStatement st = null;
		try {
			if (connection != null) {
				st = (PreparedStatement) connection
						.prepareStatement("DELETE from RATINGS where recipe_id='" + recipe.getRecipeId() + "';");
				st.execute();

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void executeGiveRating(int recipeId, int userId, int rating) {
		Connection connection = dbConnector.getDBConnection();
		CallableStatement stmt = null;
		String source = "sp_insertRating";
		try {
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?,?,?)}");
				stmt.setInt(1, userId);
				stmt.setInt(2, recipeId);
				stmt.setInt(3, rating);
				stmt.execute();
			}
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}

	}
	
	public ArrayList<Integer> GetRatingOfUser(int recipeId, int userId) {
		ArrayList<Integer> recipeRatings = new ArrayList<Integer>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_fetchRatingByUser";
		ResultSet resultSet = null;
		try {
			if (connection != null) {
				st = (CallableStatement) connection
						.prepareCall("{call " + source + "(?, ?)}");
				st.setInt(1, userId);
				st.setInt(2, recipeId);
				Boolean hasResuts = st.execute();
				if (hasResuts) {
					resultSet = st.getResultSet();
				}
				while (resultSet.next()) {
					recipeRatings.add(resultSet.getInt("recipe_rating"));
				}
			}
			logs.log(Logger.INFO, "Ratings for  " + recipeId + " are retrieved Successfully!");
		} catch (Exception e) {
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
		return recipeRatings;
	}
	
	@Override
	public int add(IRatings Obj) throws Exception {
		try {
			source = "sp_addCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getRecipeName());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		}catch (Exception e) {
			throw e;
		}	
	}
	@Override
	public int update(IRatings Obj) throws Exception {
		try {
			source = "sp_updateCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getRecipeName());
				stmt.setInt(2, Obj.getRecipeId());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		}catch (Exception e) {
			throw e;
		}
	}
	@Override
	public int remove(IRatings Obj) throws Exception {
		try {
			source = "sp_removeCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getRecipeName());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		}catch (Exception e) {
			throw e;
		}	
	}
	@Override
	public List<IRatings> Select() throws Exception {
		source = "sp_fetchDistinctRatings";
		List<IRatings> ratingsList = new ArrayList<IRatings>();
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
						ratings.setUserId(resultSet.getInt("user_id"));
						ratings.setRecipeId(resultSet.getInt("recipe_id"));
						ratings.setRatings(resultSet.getInt("recipe_rating"));
						ratings.setRecipeName(resultSet.getString("recipe_name"));				
						ratingsList.add(ratings);
					}
				}
			}
			connection.close();
			return ratingsList;			
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
			throw e;
		}
	}

}
