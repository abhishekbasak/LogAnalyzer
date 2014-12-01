package utils;

import java.util.HashMap;
import java.util.Map;

import play.Play;

public class LoggerUtils {
	
	static final String applicationName = Play.configuration.getProperty("application.name");
	static final String applicationVersion = Play.configuration.getProperty("application.version");

	public static String log(String className, String methodName, String message, Object... params) {
		String logMessage = "";
		
		try {
			Map<String, Object> logMap = new HashMap<String, Object>();
			
			if (params != null) {
				for (int i = 0; i < params.length; i = i + 2) {
					if(params[i] instanceof String) {
						logMap.put(params[i].toString(), params[i+1]);
					}
				}
			}
			
			String paramStr = "";
			if (logMap.size() > 0) {
				paramStr = ", params=" + logMap;
			}

			logMessage = "classname=" + className + ", method=" + methodName + ", " + applicationName + "=" + applicationVersion + ", " + message + paramStr;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return logMessage;
	}
}
