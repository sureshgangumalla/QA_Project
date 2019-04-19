package com.quality.project.password;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;

public class PasswordConfigDBConnector{

	public IPasswordParam executeReadPasswordConfig(String source) throws SQLException {
		DBConnector dbConnector = DBConnector.getDBConnectorInstance();
		Connection connection = dbConnector.getDBConnection();
		IPasswordParam password = new PasswordParam();
		if (connection != null) {
			CallableStatement stmt = (CallableStatement) connection
					.prepareCall("{call " + source + "}");
			Boolean hasResuts = stmt.execute();
			ResultSet resultSet = null;
			
			if (hasResuts) {
				resultSet = stmt.getResultSet();
				if (resultSet.next()) {
					password.setMinLengthOfPassword(resultSet.getInt("password_Length"));
					password.setIsHavingUppercase(resultSet.getBoolean("have_Uppercase_Character"));
					password.setIsHavingLowercase(resultSet.getBoolean("have_Lowercase_Character"));
					password.setIsHavingNumber(resultSet.getBoolean("have_Number"));
					password.setIsHavingSpecialCharacter(resultSet.getBoolean("have_Special_Symbol"));
				}
			}
		}
		return password;
	}
	
	public boolean executeSetPasswordConfig(String source, IPasswordParam password) throws SQLException {
		DBConnector dbConnector = DBConnector.getDBConnectorInstance();
		Connection connection = dbConnector.getDBConnection();
		if (connection != null) {
			CallableStatement stmt = (CallableStatement) connection
					.prepareCall("{call " + source + "(?,?,?,?,?)}");
			stmt.setInt(1, password.getMinLengthOfPassword());
			stmt.setBoolean(2, password.isHavingUppercase());
			stmt.setBoolean(3, password.isHavingLowercase());
			stmt.setBoolean(4, password.isHavingNumber());
			stmt.setBoolean(5, password.isHavingSpecialCharacter());
			
			int count = stmt.executeUpdate();
			if(1==count) {
				return true;
			}
		}
		return false;
	}
}
