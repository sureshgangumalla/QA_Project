package com.quality.project.password;

public interface IPasswordValidator {

	public void setPasswordParam(IPasswordParam passwordParam);
	public boolean isValidPassword(String password);
	public String getPasswordConfigMessage();

}
