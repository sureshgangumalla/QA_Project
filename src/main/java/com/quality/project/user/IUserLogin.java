package com.quality.project.user;

public interface IUserLogin {
	
	public boolean isValidUserEmail(String emailID);
	
	public boolean isValidPassword(String password);
	
	public boolean isValidUser(String emailID);
	
	public boolean login(String emailID,String password);

}