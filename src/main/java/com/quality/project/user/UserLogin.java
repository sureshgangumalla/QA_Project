package com.quality.project.user;

import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;

public class UserLogin implements IUserLogin {

	@Override
	public boolean isValidUser(String emailID) {
		if (null == emailID) {
			return false;
		} else if (emailID.isEmpty()) {
			return false;
		} else {
			IUser user = getUserDetailsfromDB(emailID);
			if (null != user.getUserEmailId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean login(String emailID, String password) {
		LoggerInstance logs = LoggerInstance.getInstance();
		if (null == emailID || null == password) {
			return false;
		} else if (emailID.isEmpty() || password.isEmpty()) {
			return false;
		} else {
			IUser user = getUserDetailsfromDB(emailID);
			if (null != user.getUserEmailId()) {
				if (user.getUserPassword().equals(password)) {
					ISessionHandler sessionHandler = SessionHandler
							.getSession();
					sessionHandler.setUserIntheSession(user);
					logs.log(Logger.INFO,
							"User with email " + user.getUserEmailId()
									+ " has logged in successfully");
					return true;
				}
			}
		}
		return false;
	}

	private IUser getUserDetailsfromDB(String emailID) {
		UserDatabaseManager userDB = new UserDatabaseManager();
		return userDB.getUser(emailID);
	}

	@Override
	public boolean isValidUserEmail(String emailID) {
		if (null != emailID) {
			if (!emailID.trim().isEmpty()) {
				if (emailID.contains("@")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isValidPassword(String password) {
		if (null != password) {
			if (!password.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}
}