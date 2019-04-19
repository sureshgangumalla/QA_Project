package com.quality.project.access;

import java.util.ArrayList;

public class AccessControl implements IAccessControl {

	private ArrayList<String> adminTools;
	private ArrayList<String> userTools;

	public AccessControl() {
		this.adminTools = new ArrayList<String>();
		this.userTools = new ArrayList<String>();
		addAdminTools();
		addUserTools();
	}

	@Override
	public ArrayList<String> accessAdminTools() {
		return adminTools;
	}

	@Override
	public ArrayList<String> accessUserTools() {
		return userTools;
	}

	private void addUserTools() {
		userTools.add("MyRecipes");
	}

	private void addAdminTools() {
		adminTools.add("ApproveRecipes");
		adminTools.add("ConfigurePassword");
		adminTools.add("DeleteRecipes");
	}
}
