package com.quality.project.logger;

public class WarnLogger extends Logger
{
	public WarnLogger()
	{
		super(Logger.WARN);
	}

	@Override
	protected void logMessage(String message)
	{
		logInDatabase("WARN", message);
	}
}
