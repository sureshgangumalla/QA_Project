package com.quality.project.access;

import java.util.ArrayList;

public interface IAccessControl {
	ArrayList<String> accessAdminTools();
	ArrayList<String> accessUserTools();
}
