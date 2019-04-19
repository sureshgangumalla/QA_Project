package com.quality.project.JUnit.registrationAndLoginTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.user.IUser;
import com.quality.project.user.IUserLogin;
import com.quality.project.user.UserLogin;

public class LoginTest {

	private IUserMockFactory userMockFactory;
	private IUserLogin login;
	
	@Before
    public void setUp() {
		userMockFactory = UserMockFactory.getUserMockFactory();
		login = new UserLogin();
    }
	
	@Test
	public void nullEmailUsertest() {
		IUser nullEmailUser = userMockFactory.getUser("nullEmailUser");
		assertTrue("Null Email check passed",
				!login.isValidUserEmail(nullEmailUser.getUserEmailId()));
	}

	@Test
	public void emptyEmailUsertest() {
		IUser emptyEmailUser = userMockFactory.getUser("emptyEmailUser");
		assertTrue("Empty Email check passed",
				!login.isValidUserEmail(emptyEmailUser.getUserEmailId()));
	}
	
	@Test
	public void nullPasswordUsertest() {
		IUser nullPasswordUser = userMockFactory.getUser("nullPasswordUser");
		assertTrue("Null password check passed",
				!login.isValidPassword(nullPasswordUser.getUserPassword()));
	}

	@Test
	public void emptyPasswordUsertest() {
		IUser emptyPasswordUser = userMockFactory.getUser("emptyPasswordUser");
		assertTrue("Empty password check passed",
				!login.isValidPassword(emptyPasswordUser.getUserPassword()));
	}
}
