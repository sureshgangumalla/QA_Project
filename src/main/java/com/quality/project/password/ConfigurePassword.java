package com.quality.project.password;

import java.sql.SQLException;

import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;

public class ConfigurePassword implements IConfigurePassword {

	private LoggerInstance logs;

	public ConfigurePassword() {
		logs = LoggerInstance.getInstance();
	}

	@Override
	public IPasswordParam getPasswordConfig() {
		String sp_getPwConfig = "sp_passwordConfig";
		IPasswordParam password = null;

		PasswordConfigDBConnector pwConfigWithDB = new PasswordConfigDBConnector();

		try {
			password = pwConfigWithDB.executeReadPasswordConfig(sp_getPwConfig);
		} catch (SQLException e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		return password;
	}

	@Override
	public boolean setPasswordConfig(IPasswordParam password) {

		String sp_setPwConfig = "sp_updatePasswordConfig";

		PasswordConfigDBConnector pwConfigWithDB = new PasswordConfigDBConnector();

		try {
			logs.log(Logger.INFO, "Password configuration is updated");
			return pwConfigWithDB.executeSetPasswordConfig(sp_setPwConfig,
					password);
		} catch (SQLException e) {
			logs.log(Logger.ERROR, e.getMessage());
		}
		return false;
	}
}
