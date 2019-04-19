package com.quality.project.properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:admin.properties" }, ignoreResourceNotFound = false)	
public class AdminProperties implements EnvironmentAware {
	
	private static Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		AdminProperties.environment=environment;	
	}
	
	public static String getProperty(String key) {
		return environment.getProperty(key);
	}

}
