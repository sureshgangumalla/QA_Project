package com.quality.project.access;

import java.util.ArrayList;

import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.user.IUser;

public class AccessControlProxy implements IAccessControl {
	private AccessControl realAccessControlObject;
	private boolean isAdminInSession;
	private boolean isUserInSession;

	public AccessControlProxy(IUser loggedInUser) {
		ISessionHandler sh = SessionHandler.getSession();
		isAdminInSession = sh.isAdminInSession();

		if (null != loggedInUser) {
			isUserInSession = true;
		} else {
			isUserInSession = false;
		}

	}

	@Override
	public ArrayList<String> accessAdminTools() {
		if (isAdminInSession) {
			realAccessControlObject = new AccessControl();
			return realAccessControlObject.accessAdminTools();
		}
		return null;
	}

	@Override
	public ArrayList<String> accessUserTools() {
		if (isUserInSession) {
			realAccessControlObject = new AccessControl();
			return realAccessControlObject.accessUserTools();
		}
		return null;
	}

}
