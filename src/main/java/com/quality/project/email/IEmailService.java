package com.quality.project.email;

import javax.mail.MessagingException;

public interface IEmailService {
	
	public void sendEmail(String toAddress, String subject, String messageToSend) throws MessagingException;

}
