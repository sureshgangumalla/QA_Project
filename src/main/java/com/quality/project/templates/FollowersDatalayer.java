package com.quality.project.templates;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class FollowersDatalayer {
	
	private LoggerInstance logs;
	private DBConnector dbConnector;
	
	public FollowersDatalayer() {
		dbConnector = DBConnector.getDBConnectorInstance();
		logs = LoggerInstance.getInstance();
	}
	
	
	public ArrayList<Integer> Select(int userId) {
		Connection connection = dbConnector.getDBConnection();
		ArrayList<Integer> following = new ArrayList<Integer>();
		ResultSet resultSet = null;
		CallableStatement st = null;
		String source = "sp_getFollowing";
		try {
			if(connection != null) {
				st = (CallableStatement) connection
						.prepareCall("{call " + source + "(?)}");
				st.setInt(1, userId);

				Boolean hasResults = st.execute();

				if (hasResults) {
					resultSet = st.getResultSet();
				}
				while(resultSet.next()) {		
					following.add(resultSet.getInt("follower_id"));
				}
			}
			
		}
		catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		finally {
			try {
				st.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}
		return following;
	}
	
	public boolean IsFollowing(int userId, int followerId) {
		ArrayList<Integer> followersList = this.Select(userId);
		if(followersList.contains(followerId)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void FollowUser(int userId, int followerId) {
		Connection connection = dbConnector.getDBConnection();
		CallableStatement st = null;
		String source = "sp_followUser";
		try {
			if(connection != null) {
				st = (CallableStatement) connection
						.prepareCall("{call " + source + "(?, ?)}");
				st.setInt(1, userId);
				st.setInt(2, followerId);
				st.execute();
			}
			
		}
		catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}
	}
	
	public ArrayList<Integer> getFollowersId(int followeeId){
		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement stmt = null;
		String source = "sp_fetchFollwersforUser";
		ArrayList<Integer> followers = new ArrayList<Integer>();
		try {
			if(connection != null) {
				stmt = (CallableStatement) connection
						.prepareCall("{call " + source + "(?)}");
				stmt.setInt(1, followeeId);
				Boolean hasResuts = stmt.execute();

				if (hasResuts) {
					resultSet = stmt.getResultSet();
				}
				while(resultSet.next()) {		
					followers.add(resultSet.getInt("user_id"));
				}
			}
		}
		catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		finally {
			try {
				stmt.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}
		return followers;
	}
}
