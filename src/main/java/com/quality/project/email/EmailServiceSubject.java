package com.quality.project.email;

import java.util.ArrayList;

import javax.mail.MessagingException;

import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class EmailServiceSubject implements IEmailServiceSubject {

	ArrayList<String> followerEmailAddresses;
	private IEmailService emailService;

	public EmailServiceSubject() {
		followerEmailAddresses = new ArrayList<String>();
		emailService = new EmailService();
	}

	@Override
	public void addFollowerEmail(String followerEmail) {
		followerEmailAddresses.add(followerEmail);
	}

	@Override
	public void removeFollowerEmail(String followerEmail) {
		followerEmailAddresses.remove(followerEmail);
	}

	@Override
	public void sendEmailToFollowers(String followeeName, String recipeName) {
		LoggerInstance logs = LoggerInstance.getInstance();
		for (String followerAddress : followerEmailAddresses) {
			try {
				emailService.sendEmail(followerAddress,
						"New Recipe added by " + followeeName,
						"Hello, You are following " + followeeName
								+ " in Recipe App. " + followeeName
								+ " has added new recipe " + recipeName);
				logs.log(Logger.INFO,
						"Notification email sent to " + followerAddress
								+ " about new recipe added by" + followeeName);
			} catch (MessagingException e) {
				logs.log(Logger.ERROR,e.getMessage());
			}
		}
		removeFollowers();
	}

	private void removeFollowers() {
		followerEmailAddresses.removeAll(followerEmailAddresses);
	}

}
