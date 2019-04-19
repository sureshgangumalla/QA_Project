package com.quality.project.logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.email.EmailService;
import com.quality.project.properties.ApplicationProperties;

/*We took the idea of Rob's chain of responsibility logger class for our project*/
public abstract class Logger {
	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	public static final int FATAL = 3;

	SimpleDateFormat dateFormat;
	Date currentLoggerTime;
	protected static final String LOG_FORMAT = "%s\t%s\t%s\t%n";

	protected int logLevel;

	protected Logger nextLogger;

	public Logger(int level) {
		nextLogger = null;
		logLevel = level;
		dateFormat = new SimpleDateFormat(
				ApplicationProperties.getProperty("spring.app.dateformat"));
	}

	public void setNextLogger(Logger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void log(int level, String message) {
		if (logLevel == level) {
			logMessage(message);
		} else if (null != nextLogger) {
			nextLogger.log(level, message);
		} else if (null == nextLogger) {
			log(INFO, "Invalid log level information. Setting it to INFO");
			log(INFO, message);
		}
	}

	protected abstract void logMessage(String message);

	protected void logInConsole(String logLevel, String msg) {
		currentLoggerTime = new Date();
		System.out.printf(Logger.LOG_FORMAT, currentLoggerTime, logLevel, msg);
	}

	protected void logInDatabase(String logLevel, String msg) {
		currentLoggerTime = new Date();
		String sp_name = "sp_addLog";
		DBConnector dbConnector = DBConnector.getDBConnectorInstance();
		Connection connection = dbConnector.getDBConnection();
		String datetime = dateFormat.format(currentLoggerTime);
		CallableStatement stmt;
		EmailService email = new EmailService();
		try {
			stmt = (CallableStatement) connection
					.prepareCall("{call " + sp_name + "(?,?,?)}");

			stmt.setString("logTime", datetime);
			stmt.setString("logLevel", logLevel);
			stmt.setString("logMessage", msg);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException sqlFailException) {
			System.out.println(sqlFailException.toString());
		} finally {
			try {
				connection.close();
			} catch (SQLException SQLFailException) {
				try {
					email.sendEmail(
							ApplicationProperties
									.getProperty("spring-mail.smtp.username"),
							"Database error occured for the Recipe App",
							SQLFailException.toString());
				} catch (MessagingException msgException) {
					System.out.println(msgException.getMessage());
				}
			}
		}
	}
}