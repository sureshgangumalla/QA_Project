package com.quality.project.password;

public interface IPasswordParam {

	public void setMinLengthOfPassword(int length);

	public int getMinLengthOfPassword();

	public boolean isHavingLowercase();

	public void setIsHavingLowercase(boolean isHavingLowercase);

	public boolean isHavingUppercase();

	public void setIsHavingUppercase(boolean isHavingUppercase);

	public boolean isHavingNumber();

	public void setIsHavingNumber(boolean isHavingNumber);

	public boolean isHavingSpecialCharacter();

	public void setIsHavingSpecialCharacter(boolean isHavingSpecialCharacter);
}
