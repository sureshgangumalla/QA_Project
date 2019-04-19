package com.quality.project.JUnit.registrationAndLoginTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.JUnit.passwordConfigTest.IPasswordMock;
import com.quality.project.JUnit.passwordConfigTest.PasswordMock;
import com.quality.project.password.IPasswordValidator;
import com.quality.project.password.PasswordValidator;
import com.quality.project.user.IRegistration;
import com.quality.project.user.IUser;
import com.quality.project.user.Registration;

public class RegistrationTest {
	
	private IUserMockFactory userMockFactory;
	private IRegistration registration;
	private IPasswordValidator pwValidator;
	
	@Before
    public void setUp() {
		userMockFactory = UserMockFactory.getUserMockFactory();		
		pwValidator = new PasswordValidator();
		IPasswordMock pwMock = PasswordMock.getPasswordMock();
		pwValidator.setPasswordParam(pwMock.getPasswordMock("everything"));
		registration = new Registration(pwValidator);
    }

	@Test
	public void nullNameUsertest() {
		IUser nullNameUser = userMockFactory.getUser("nullNameUser");
		assertTrue("Null username check passed",
				!registration.isValidUsername(null));
	}

	@Test
	public void emptyNameUsertest() {
		IUser emptyNameUser = userMockFactory.getUser("emptyNameUser");
		assertTrue("Empty username check passed",
				!registration.isValidUsername(emptyNameUser.getUsername()));
	}

	@Test
	public void nullEmailUsertest() {
		IUser nullEmailUser = userMockFactory.getUser("nullEmailUser");
		assertTrue("Null Email check passed",
				!registration.isValidEmail(nullEmailUser.getUserEmailId()));
	}

	@Test
	public void emptyEmailUsertest() {
		IUser emptyEmailUser = userMockFactory.getUser("emptyEmailUser");
		assertTrue("Empty Email check passed",
				!registration.isValidEmail(emptyEmailUser.getUserEmailId()));
	}

	@Test
	public void invalidEmailUsertest() {
		IUser invalidEmailUser = userMockFactory.getUser("invalidEmailUser");
		assertTrue("Invalid Email check passed",
				!registration.isValidEmail(invalidEmailUser.getUserEmailId()));
	}

	@Test
	public void nullPhoneUsertest() {
		IUser nullPhoneUser = userMockFactory.getUser("nullPhoneUser");
		assertTrue("Null Phone check passed",
				!registration.isValidPhoneNumber(nullPhoneUser.getUserPhoneNumber()));
	}

	@Test
	public void emptyPhoneUsertest() {
		IUser emptyPhoneUser = userMockFactory.getUser("emptyPhoneUser");
		assertTrue("Empty Phone check passed",
				!registration.isValidPhoneNumber(emptyPhoneUser.getUserPhoneNumber()));
	}

	@Test
	public void nullPasswordUsertest() {
		IUser nullPasswordUser = userMockFactory.getUser("nullPasswordUser");
		assertTrue("Null password check passed",
				!registration.isValidPassword(nullPasswordUser.getUserPassword()));
	}

	@Test
	public void emptyPasswordUsertest() {
		IUser emptyPasswordUser = userMockFactory.getUser("emptyPasswordUser");
		assertTrue("Empty password check passed",
				!registration.isValidPassword(emptyPasswordUser.getUserPassword()));
	}
	
	@Test
	public void RightPasswordUsertest() {
		IUser rightPasswordUser = userMockFactory.getUser("rightPasswordUser");
		assertTrue("Right password check passed",
				registration.isValidPassword(rightPasswordUser.getUserPassword()));
	}
}
