package com.quality.project.session;

import com.quality.project.user.IUser;

public interface ISessionHandler {
	
	public boolean isAdminInSession();
	
	public void setAdminInSession();
	
	public IUser getUserIntheSession();
	
	public void setUserIntheSession(IUser user);
	
	public void startSession();
	
	public void endSession();
	
	public boolean isSessionStarted();
}
