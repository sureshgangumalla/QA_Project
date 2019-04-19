package com.quality.project.user;

import com.quality.project.properties.AdminProperties;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;

public class AdminLogin implements IAdminLogin {
	
	private boolean isValidAdminPassword(String password) {
		if(AdminProperties.getProperty("app.admin.password").equals(password)) {
			return true;
		}
		return false;
	}
	
	public boolean ifValidAdminSetInSession(String password) {
		
		ISessionHandler session = SessionHandler.getSession();
		
		if(isValidAdminPassword(password)) {
			session.setAdminInSession();
			return true;
		}		
		return false;
	}

}
