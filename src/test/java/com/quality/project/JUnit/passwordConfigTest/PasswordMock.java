package com.quality.project.JUnit.passwordConfigTest;

import java.util.HashMap;
import java.util.Map;

import com.quality.project.password.IPasswordParam;
import com.quality.project.password.PasswordParam;

public class PasswordMock implements IPasswordMock {

	private static IPasswordMock passwordMock;
	private static Map<String, IPasswordParam> passwords;

	private PasswordMock() {
		passwords = new HashMap<String, IPasswordParam>();
	}

	public static IPasswordMock getPasswordMock() {
		if (null == passwordMock) {
			passwordMock = new PasswordMock();
			loadPassword();
		}
		return passwordMock;
	}

	private static void loadPassword() {
		IPasswordParam pwParam = new PasswordParam();
		pwParam.setMinLengthOfPassword(9);
		pwParam.setIsHavingLowercase(true);
		pwParam.setIsHavingUppercase(true);
		pwParam.setIsHavingNumber(true);
		pwParam.setIsHavingSpecialCharacter(true);

		passwords.put("everything", pwParam);

		pwParam.setMinLengthOfPassword(10);
		pwParam.setIsHavingLowercase(false);
		pwParam.setIsHavingUppercase(true);
		pwParam.setIsHavingNumber(false);
		pwParam.setIsHavingSpecialCharacter(true);

		passwords.put("nolowerandnumber", pwParam);
	}

	@Override
	public IPasswordParam getPasswordMock(String identifier) {
		return passwords.get(identifier);
	}

}
