package com.quality.project.JUnit.registrationAndLoginTest;

import java.util.HashMap;
import java.util.Map;

import com.quality.project.user.IUser;
import com.quality.project.user.User;

public class UserMockFactory implements IUserMockFactory {

	private static Map<String, IUser> usersMap;

	private static IUserMockFactory userMockFactory;

	public static IUserMockFactory getUserMockFactory() {
		if (null == userMockFactory) {
			userMockFactory = new UserMockFactory();
		}
		return userMockFactory;
	}

	private UserMockFactory() {
		this.usersMap = new HashMap<String, IUser>();
		loadUsers();
	}

	private void loadUsers() {
		IUser nullNameUser = new User(1,null, "email@dal.ca", "phoneNumber",
				"password");
		usersMap.put("nullNameUser", nullNameUser);

		IUser emptyNameUser = new User(2,"  ", "email@dal.ca", "phoneNumber",
				"password");
		usersMap.put("emptyNameUser", emptyNameUser);

		IUser nullEmailUser = new User(3,"name", null, "phoneNumber", "password");
		usersMap.put("nullEmailUser", nullEmailUser);

		IUser emptyEmailUser = new User(4,"name", " ", "902123456", "password");
		usersMap.put("emptyEmailUser", emptyEmailUser);

		IUser invalidEmailUser = new User(5,"name", "invalidEmail", "902123456",
				"password");
		usersMap.put("invalidEmailUser", invalidEmailUser);

		IUser nullPhoneUser = new User(6,"name", "email@dal.ca", null,
				"password");
		usersMap.put("nullPhoneUser", nullPhoneUser);

		IUser emptyPhoneUser = new User(7,"name", "email@dal.ca", "  ",
				"password");
		usersMap.put("emptyPhoneUser", emptyPhoneUser);

		IUser nullPasswordUser = new User(8,"name", "email@dal.ca", "902123456",
				null);
		usersMap.put("nullPasswordUser", nullPasswordUser);

		IUser emptyPasswordUser = new User(9,"name", "email@dal.ca",
				"902123456", "  ");
		usersMap.put("emptyPasswordUser", emptyPasswordUser);
		
		IUser rightPasswordUser = new User(10,"name", "email@dal.ca",
				"902123456", "Qwerty@123");
		usersMap.put("rightPasswordUser", rightPasswordUser);
	}

	@Override
	public IUser getUser(String key) {
		return usersMap.get(key);
	}

}
