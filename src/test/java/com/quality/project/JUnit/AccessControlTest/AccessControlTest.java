package com.quality.project.JUnit.AccessControlTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.access.AccessControl;
import com.quality.project.access.AccessControlProxy;
import com.quality.project.access.IAccessControl;
import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;
import com.quality.project.user.IUser;
import com.quality.project.user.User;

public class AccessControlTest {
	private IAccessControl accessControl;
	private ArrayList<String> userTools;
	private ArrayList<String> adminTools;
	private ISessionHandler session;

	@Before
	public void setUp() {
		accessControl = new AccessControl();
		session = SessionHandler.getSession();
		adminTools = new ArrayList<String>();
		userTools = new ArrayList<String>();
		adminTools.add("ApproveRecipes");
		adminTools.add("ConfigurePassword");
		adminTools.add("DeleteRecipes");
		userTools.add("MyRecipes");

	}

	@Test
	public void testAdminToolsAccess() {

		session.setAdminInSession();
		IUser user = new User();
		accessControl = new AccessControlProxy(user);
		assertTrue(adminTools.equals(accessControl.accessAdminTools()));
		session.endSession();
	}

	@Test
	public void testLoggedInUserToolsAccess() {

		IUser user = new User();
		user.setUserID(999);
		user.setUsername("test");
		accessControl = new AccessControlProxy(user);
		assertTrue(userTools.equals(accessControl.accessUserTools()));
	}

	@Test
	public void testNotLoggedUserToolsAccess() {

		IUser user = session.getUserIntheSession();
		accessControl = new AccessControlProxy(user);
		assertTrue(null == accessControl.accessUserTools());
	}

}
