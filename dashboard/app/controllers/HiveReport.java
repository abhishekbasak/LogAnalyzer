package controllers;

import java.util.Map;
import java.util.Map.Entry;

import models.AppLogger;


public class HiveReport extends Application {
	
	public static void studentCount() {
		/*List<String> abc =  new ArrayList();
		//abc[0] = "0";
		//abc[1] = "1";
		abc.add("0");
		abc.add("1");
		abc.add("2");
		abc.add("3");*/
//		String studentCount = "10,221,32,443,54,65,76,87,98,109,110,111,112,113,114,215,116,217,318,149,210,241,222,243";
//		String categories = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23";
//		render(categories,studentCount);
		
		
		Map<String, Double> hourlyCountMap = AppLogger.getHourlyCount(null);
		
		String studentCount = "";
		String categories = "";
		if (hourlyCountMap != null && !hourlyCountMap.isEmpty()) {
			for (Entry<String, Double> entry : hourlyCountMap.entrySet()) {
				if ("".equals(categories)) {
					categories += entry.getKey();
				} else {
					categories += "," + entry.getKey();
				}
				
				if ("".equals(studentCount)) {
					studentCount += entry.getValue();
				} else {
					studentCount += "," + entry.getValue();
				}
			}
		}
		
		render(categories,studentCount);
	}
}
