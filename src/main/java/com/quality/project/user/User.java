package com.quality.project.user;

public class User implements IUser {
	
	private int userId;
	private String fullName;
	private String emailID;
	private String phoneNumber;
	private String password;
	
	public User() {}
	
	public User(int user_id,String fullName, String emailId, String phoneNumber, String password) {
		this.setUserID(user_id);
		this.setUsername(fullName);
		this.setUserEmailId(emailId);
		this.setUserPhoneNumber(phoneNumber);
		this.setUserPassword(password);
	}
	
	public int getUserID() {
		return this.userId;
	}
	
	public void setUserID(int ID) {
		this.userId = ID;
	}
	
	@Override
	public String getUsername() {
		return this.fullName;
	}
	
	@Override
	public String getUserEmailId() {
		return this.emailID;
	}
	
	@Override
	public String getUserPhoneNumber() {
		return this.phoneNumber;
	}
	
	@Override
	public String getUserPassword() {
		return this.password;
	}

	@Override
	public void setUsername(String username) {
		this.fullName = username;
	}

	@Override
	public void setUserEmailId(String emailID) {
		this.emailID = emailID;		
	}

	@Override
	public void setUserPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void setUserPassword(String password) {
		this.password = password;
	}
}
