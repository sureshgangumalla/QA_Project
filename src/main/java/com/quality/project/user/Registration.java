package com.quality.project.user;

import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.password.IPasswordValidator;
import com.quality.project.password.PasswordEncrypt;

public class Registration implements IRegistration {

	PasswordEncrypt encryptPassword;
	private String passwordConfigMsg;
	private IPasswordValidator pwValidator;

	public Registration(IPasswordValidator pwValidator) {
		this.encryptPassword = new PasswordEncrypt();
		this.pwValidator = pwValidator;
	}

	@Override
	public boolean register(IUser user) {
		
		boolean isExistingUser = false;
		UserDatabaseManager userDB = new UserDatabaseManager();
		IUserLogin login = new UserLogin();
		LoggerInstance logs = LoggerInstance.getInstance();
		isExistingUser = login.isValidUser(user.getUserEmailId());

		if (!isExistingUser) {
			String password = PasswordEncrypt
					.hashPassword(user.getUserPassword());
			user.setUserPassword(password);

			int response = userDB.addUser(user);
			if (1 == response) {
				logs.log(Logger.INFO, "User with email "+user.getUserEmailId()+ " has signedup successfully");
				login.login(user.getUserEmailId(), user.getUserPassword());				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidUsername(String username) {
		if (null != username) {
			if (!username.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidEmail(String userEmail) {
		if (null != userEmail) {
			if (!userEmail.trim().isEmpty()) {
				if (userEmail.contains("@")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isValidPhoneNumber(String userPhoneNumber) {
		if (null != userPhoneNumber) {
			if (!userPhoneNumber.trim().isEmpty()) {
				try {
					Long.parseLong(userPhoneNumber);
				} catch (NumberFormatException e) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidPassword(String userPassword) {

		if (null != userPassword) {
			if (userPassword.trim().isEmpty()) {
				passwordConfigMsg = "Please enter valid password";
				return false;
			}
			if (pwValidator.isValidPassword(userPassword)) {
				return true;
			}
			passwordConfigMsg = pwValidator.getPasswordConfigMessage();
		}
		return false;
	}

	@Override
	public String getPasswordConfigMsg() {
		return passwordConfigMsg;
	}

}