package com.quality.project.JUnit.sessionHandlerTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.quality.project.JUnit.registrationAndLoginTest.IUserMockFactory;
import com.quality.project.JUnit.registrationAndLoginTest.UserMockFactory;
import com.quality.project.password.IPasswordParam;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;

public class SessionHandlerTest {

	@Test
	public void noUserInSessionTest() {
		
		ISessionHandler session = SessionHandler.getSession();
		session.endSession();
		assertTrue("Password Configuration passed", (null == session.getUserIntheSession()));
	}
	
	@Test
	public void UserInSessionTest() {
		ISessionHandler session = SessionHandler.getSession();
		IUserMockFactory userMck = UserMockFactory.getUserMockFactory();
		session.endSession();
		session.setUserIntheSession(userMck.getUser("rightPasswordUser"));
		assertTrue("Password Configuration passed", !(null == session.getUserIntheSession()));
	}
	
	@Test
	public void AdminInSessionTest() {
		
		ISessionHandler session = SessionHandler.getSession();
		session.endSession();
		session.setAdminInSession();
		assertTrue("Password Configuration passed", (null == session.getUserIntheSession() && session.isAdminInSession()));
	}
	
	@Test
	public void EndSessionTest() {
		ISessionHandler session = SessionHandler.getSession();
		session.endSession();
		assertTrue("Password Configuration passed", (null == session.getUserIntheSession() && !session.isAdminInSession() && !session.isSessionStarted()));
	}	
}
