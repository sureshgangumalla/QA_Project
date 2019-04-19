package com.quality.project.password;

public class PasswordParam implements IPasswordParam{
	
	private int minLengthOfPassword;
	private boolean isHavingLowercase;
	private boolean isHavingUppercase;
	private boolean isHavingNumber;
	private boolean isHavingSpecialCharacter;

	@Override
	public void setMinLengthOfPassword(int length) {
		minLengthOfPassword = length;
	}
	
	@Override
	public int getMinLengthOfPassword() {
		return minLengthOfPassword;
	}

	@Override
	public boolean isHavingLowercase() {
		return isHavingLowercase;
	}

	@Override
	public void setIsHavingLowercase(boolean isHavingLowercase) {
		this.isHavingLowercase = isHavingLowercase;
	}

	@Override
	public boolean isHavingUppercase() {
		return isHavingUppercase;
	}

	@Override
	public void setIsHavingUppercase(boolean isHavingUppercase) {
		this.isHavingUppercase = isHavingUppercase;
	}

	@Override
	public boolean isHavingNumber() {
		return isHavingNumber;
	}

	@Override
	public void setIsHavingNumber(boolean isHavingNumber) {
		this.isHavingNumber = isHavingNumber;
	}

	@Override
	public boolean isHavingSpecialCharacter() {
		return isHavingSpecialCharacter;
	}

	@Override
	public void setIsHavingSpecialCharacter(boolean isHavingSpecialCharacter) {
		this.isHavingSpecialCharacter = isHavingSpecialCharacter;
	}
}
