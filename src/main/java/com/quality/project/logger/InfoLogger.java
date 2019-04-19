package com.quality.project.logger;

public class InfoLogger extends Logger
{
	public InfoLogger()
	{
		super(Logger.INFO);
	}

	@Override
	protected void logMessage(String message)
	{
		logInDatabase("INFO", message);
	}	
}