package com.quality.project.logger;

import javax.mail.MessagingException;

import com.quality.project.email.EmailService;
import com.quality.project.properties.ApplicationProperties;

public class FatalLogger extends Logger
{
	public FatalLogger()
	{
		super(Logger.FATAL);
	}

	@Override
	protected void logMessage(String message)
	{
		System.out.println(message);
		sendEmail(message);
	}
	
	private void sendEmail(String message)
	{
		EmailService email = new EmailService();
		try {
			email.sendEmail(ApplicationProperties.getProperty("spring-mail.smtp.username"), "Database error occured for the Recipe App", message);
		} catch (MessagingException msgException) {
			System.out.println(msgException.getMessage());
		}
		
	}
}

