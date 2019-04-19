package com.quality.project.databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.properties.ApplicationProperties;

public class DBConnector {

	private static DBConnector dbConnector = null;
	LoggerInstance logs = LoggerInstance.getInstance();

	private DBConnector() {
	}

	public static DBConnector getDBConnectorInstance() {

		if (null == dbConnector) {
			dbConnector = new DBConnector();
		}
		return dbConnector;
	}

	public Connection getDBConnection() {

		Connection connection = null;

		try {
			String databaseURl = ApplicationProperties.getProperty("spring.datasource.url");
			String username = ApplicationProperties.getProperty("spring.datasource.username");
			String password = ApplicationProperties.getProperty("spring.datasource.password");

			Class.forName(ApplicationProperties.getProperty("spring.datasource.driver-class-name"));

			connection = DriverManager.getConnection(databaseURl, username, password);
		} catch (Exception e) {

		}
		return connection;
	}
}
