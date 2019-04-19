package com.quality.project.JUnit.passwordConfigTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.password.IPasswordParam;
import com.quality.project.password.IPasswordValidator;
import com.quality.project.password.PasswordValidator;

public class PasswordValidatorTest {
	
	IPasswordValidator passwordValidator;
	IPasswordMock mock;
	
	@Before
    public void setUp() {
		passwordValidator = new PasswordValidator();
		mock = PasswordMock.getPasswordMock();
    }
	
	@Test
	public void passwordContainsEverythingTest() {
		IPasswordParam pwParam = mock.getPasswordMock("everything");
		passwordValidator.setPasswordParam(pwParam);
		assertTrue("Password Configuration passed", passwordValidator.isValidPassword("Qwe!rty@123"));
	}
	
	@Test
	public void passwordContainsNoLowerNoNumberTest() {
		IPasswordParam pwParam = mock.getPasswordMock("nolowerandnumber");
		passwordValidator.setPasswordParam(pwParam);
		assertTrue("Password Configuration passed", passwordValidator.isValidPassword("QWERTY@ABC"));
	}
	
	@Test
	public void passwordWithLessNumberTest() {
		IPasswordParam pwParam = mock.getPasswordMock("nolowerandnumber");
		passwordValidator.setPasswordParam(pwParam);
		assertTrue("Password Configuration passed", !passwordValidator.isValidPassword("QWEY@ABC"));
	}

}
