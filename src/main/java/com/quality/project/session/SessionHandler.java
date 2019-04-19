package com.quality.project.session;

import com.quality.project.user.IUser;

public class SessionHandler implements ISessionHandler {

	private IUser userInSession;
	private boolean isAdminInSession;
	private boolean isSessionStarted;
	private static ISessionHandler sessionHandler;

	private SessionHandler() {
		userInSession = null;
		isAdminInSession = false;
		
	}

	public static ISessionHandler getSession() {
		if (null == sessionHandler) {
			sessionHandler = new SessionHandler();
		}
		return sessionHandler;
	}

	@Override
	public boolean isAdminInSession() {
		return isAdminInSession;
	}

	@Override
	public void setAdminInSession() {
		if (!isSessionStarted) {
			isAdminInSession = true;
			startSession();
		}
	}

	@Override
	public IUser getUserIntheSession() {
		return userInSession;
	}

	@Override
	public void setUserIntheSession(IUser user) {
		if (!isAdminInSession && !isSessionStarted) {
			userInSession = user;
			startSession();
		}
	}

	@Override
	public void endSession() {
		isSessionStarted = false;
		userInSession = null;
		isAdminInSession = false;
	}

	@Override
	public boolean isSessionStarted() {
		return isSessionStarted;
	}

	@Override
	public void startSession() {
		isSessionStarted = true;

	}

}
