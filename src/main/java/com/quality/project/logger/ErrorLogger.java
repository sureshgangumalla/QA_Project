package com.quality.project.logger;

public class ErrorLogger extends Logger
{
	public ErrorLogger()
	{
		super(Logger.ERROR);
	}

	@Override
	protected void logMessage(String message)
	{
		logInDatabase("ERROR", message);
	}
	
}
