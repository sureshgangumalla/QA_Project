package com.quality.project.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class UserDatabaseManager {

	private DBConnector dbConnector;
	private LoggerInstance logs;

	public UserDatabaseManager() {
		logs = LoggerInstance.getInstance();
		dbConnector = DBConnector.getDBConnectorInstance();
	}

	public int addUser(IUser obj) {
		int count = 0;
		String sp_name = "sp_addUser";
		Connection connection = dbConnector.getDBConnection();
		if (connection != null) {
			CallableStatement stmt;
			try {
				stmt = (CallableStatement) connection
						.prepareCall("{call " + sp_name + "(?,?,?,?)}");
				stmt.setString("full_name", obj.getUsername());
				stmt.setString("email_address", obj.getUserEmailId());
				stmt.setString("phone_number", obj.getUserPhoneNumber());
				stmt.setString("user_password", obj.getUserPassword());
				count = stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}
		return count;
	}

	public IUser getUser(String userEmail) {
		IUser user = new User();
		ResultSet resultSet = null;
		CallableStatement stmt;
		String sp_name = "sp_fetchUser";
		Connection connection = dbConnector.getDBConnection();
		if (connection != null) {
			try {
				stmt = (CallableStatement) connection
						.prepareCall("{call " + sp_name + "(?)}");
				stmt.setString(1, userEmail);
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					if (resultSet.next()) {
						user.setUserID(resultSet.getInt("user_id"));
						user.setUsername(resultSet.getString("full_name"));
						user.setUserEmailId(
								resultSet.getString("email_address"));
						user.setUserPhoneNumber(
								resultSet.getString("phone_number"));
						user.setUserPassword(
								resultSet.getString("user_password"));
						resultSet.close();
					}
				}
				stmt.close();
			} catch (SQLException e) {
				logs.log(Logger.ERROR, e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}
		return user;
	}
}
