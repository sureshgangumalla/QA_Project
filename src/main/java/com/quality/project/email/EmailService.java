package com.quality.project.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.quality.project.properties.ApplicationProperties;

@Service
public class EmailService implements IEmailService {
	
	private Properties emailProperties;
	private InternetAddress sourceAddress;
	private Session session;
	
	private void createSourceAddress(String userName) throws AddressException {
		sourceAddress = new InternetAddress(userName);
	}
	
	private void initializeSession() {
		final String username = ApplicationProperties.getProperty("spring-mail.smtp.username");
		final String password = ApplicationProperties.getProperty("spring-mail.smtp.password");
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		session = Session.getInstance(emailProperties, authenticator);
	}
	
	private void setProperties() {
		emailProperties = new Properties();
		emailProperties.put("mail.smtp.host", ApplicationProperties.getProperty("spring-mail.smtp.host"));
		emailProperties.put("mail.smtp.port", ApplicationProperties.getProperty("spring-mail.smtp.port"));
		emailProperties.put("mail.smtp.auth", ApplicationProperties.getProperty("spring-mail.smtp.auth"));
		emailProperties.put("mail.smtp.starttls.enable", ApplicationProperties.getProperty("spring-mail.smtp.starttls.enable"));
	}
	
	public void sendEmail(String toAddress, String subject, String messageToSend) throws MessagingException {

		setProperties();
		initializeSession();
		createSourceAddress(ApplicationProperties.getProperty("spring-mail.smtp.username"));

		Message message = new MimeMessage(session);
		message.setFrom(sourceAddress);
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		message.setRecipients(Message.RecipientType.TO, toAddresses);
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setContent(messageToSend, "text/html");

		Transport.send(message);
	}

}
