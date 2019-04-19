package com.quality.project.password;

public class PasswordValidator implements IPasswordValidator{
	
	private IPasswordParam passwordParam;
	public String passwordConfigMsg;
	
	@Override
	public void setPasswordParam(IPasswordParam passwordParam) {
		this.passwordParam = passwordParam;
		buildPasswordConfigMsg();		
	}
	
	private void buildPasswordConfigMsg() {
		passwordConfigMsg = "Password shoud contain at least " + passwordParam.getMinLengthOfPassword()+" characters";
		if(passwordParam.isHavingLowercase()) {
			passwordConfigMsg = passwordConfigMsg + " | at least one lower case";
		}
		
		if(passwordParam.isHavingNumber()){
			passwordConfigMsg = passwordConfigMsg + " | at least one number";
		}
		
		if(passwordParam.isHavingUppercase()){
			passwordConfigMsg = passwordConfigMsg + " | at least one upper case";
		}
		
		passwordConfigMsg = passwordConfigMsg + ".";
		
	}
	

	@Override
	public boolean isValidPassword(String password) {
		
		boolean retVal = true;
		
		if(password.length() < passwordParam.getMinLengthOfPassword()) {
			retVal = false;
		}
		
		if(passwordParam.isHavingLowercase()) {
			if(!hasLowerCase(password)) {
				retVal = false;
			}
		}
		
		if(passwordParam.isHavingNumber()) {
			if(!hasNumber(password)) {
				retVal = false;
			}
		}
		
		if(passwordParam.isHavingUppercase()) {
			if(!hasUpperCase(password)) {
				retVal = false;
			}
		}
		
		if(passwordParam.isHavingSpecialCharacter()) {
			if(!hasSpecialCharacter(password)) {
				retVal = false;
			}
		}		
		return retVal;
	}
	
	public boolean hasLowerCase(String password) {		
		for(int i=0; i<password.length();i++) {
			char ch = password.charAt(i);
			if(Character.isLowerCase(ch)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasUpperCase(String password) {		
		for(int i=0; i<password.length();i++) {
			char ch = password.charAt(i);
			if(Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasNumber(String password) {		
		for(int i=0; i<password.length();i++) {
			char ch = password.charAt(i);
			if(Character.isDigit(ch)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasSpecialCharacter(String password){
		for(int i=0; i<password.length();i++) {
			char ch = password.charAt(i);
			if(!Character.isDigit(ch) && !Character.isUpperCase(ch) && !Character.isLowerCase(ch)) {
				return true;
			}
		}
		return false;		
	}
	
	public String getPasswordConfigMessage(){		
		return passwordConfigMsg;		
	}
}
