package com.quality.project.user;

public interface IUser {

	public int getUserID();
	public void setUserID(int ID);
	public String getUsername();
	public String getUserEmailId();
	public String getUserPhoneNumber();
	public String getUserPassword();
	public void setUsername(String username);
	public void setUserEmailId(String emailID);
	public void setUserPhoneNumber(String phoneNumber);
	public void setUserPassword(String password);
}
