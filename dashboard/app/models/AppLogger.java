package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import utils.Constants;

public class AppLogger {


	public static Map<String, Double> getHourlyCount(String currentDate) {
		Map<String, Double> hourlyCountMap = new LinkedHashMap<String, Double>();
		
		try {
			String query = null;
			if (currentDate == null) {
				query = "SELECT HOUR(time) hour, COUNT(1) cnt FROM APPLOGGER WHERE event_message like '%Test Started%' GROUP BY HOUR(time) Order By hour";
			} else {
				
			}
			
			ResultSet rs = getResult(query);
			if (rs != null) {
				while (rs.next()) {
					hourlyCountMap.put(rs.getString("hour"), rs.getDouble("cnt"));
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return hourlyCountMap;
	}

	private static ResultSet getResult(String query) {
		ResultSet res = null;
		
		try {
			String driverName = "org.apache.hive.jdbc.HiveDriver";
			Class.forName(driverName);
			
			Connection con = DriverManager.getConnection("jdbc:hive2://" + Constants.HIVE_HOST_NAME + ":" + Constants.HIVE_PORT + "/default",
					Constants.HIVE_UID, Constants.HIVE_PWD);
			Statement stmt = con.createStatement();
			res = stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
