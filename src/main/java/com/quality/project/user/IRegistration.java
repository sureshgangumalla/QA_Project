package com.quality.project.user;

public interface IRegistration {
	public boolean isValidUsername(String username);
	public boolean isValidEmail(String userEmail);
	public boolean isValidPhoneNumber(String userPhoneNumber);
	public boolean isValidPassword(String userPassword);
	public boolean register(IUser user);
	public String getPasswordConfigMsg();
}
