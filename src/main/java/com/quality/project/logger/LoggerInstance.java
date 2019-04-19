package com.quality.project.logger;

public class LoggerInstance {
	
	private Logger logger;
	
	private static LoggerInstance loggerInstance;
	
	private LoggerInstance() {
		logger = new InfoLogger();
		ErrorLogger errorLogger = new ErrorLogger();
		errorLogger.setNextLogger(new WarnLogger());
		logger.setNextLogger(errorLogger);
		WarnLogger warnLogger = new WarnLogger();
		errorLogger.setNextLogger(warnLogger);
		FatalLogger fatalLogger = new FatalLogger();
		warnLogger.setNextLogger(fatalLogger);
	}
	
	public static LoggerInstance getInstance() {
		if(null == loggerInstance) {
			loggerInstance = new LoggerInstance();
		}
		return loggerInstance;
	}
	
	public void log(int level,String msg) {
		logger.log(level, msg);		
	}

}
