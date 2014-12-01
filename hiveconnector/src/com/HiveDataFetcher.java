package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.data.category.DefaultCategoryDataset;

public class HiveDataFetcher {

	private String hiveHostName = "127.0.0.1"; // change this ip address if
												// required

	private String hiveForwardedPort = "10000"; // this is the port number, which
												// is configured to forward to
												// Sandbox's 10000 port

	public DefaultCategoryDataset getDataFromHive() throws SQLException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet rs = runQueryAndGetResult();
		System.out.println("Populating dataset for chart...");
		while (rs.next()) {
			 dataset.setValue(rs.getDouble("cnt"), "Hour", rs.getString("hour"));
		}
		System.out.println("Dataset population complete!");
		return dataset;
	}

	private ResultSet runQueryAndGetResult() throws SQLException {
		try {
			String driverName = "org.apache.hive.jdbc.HiveDriver";
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		Connection con = DriverManager.getConnection("jdbc:hive2://"
				+ hiveHostName + ":" + hiveForwardedPort + "/default", "hue", "");
		Statement stmt = con.createStatement();
		String sql = "SELECT HOUR(time) hour, COUNT(1) cnt FROM APPLOGGER WHERE event_message like '%Test Started%' GROUP BY HOUR(time)";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		System.out.println("Query execution complete");
		return res;
	}
	
	public static void main (String[] args) {
		HiveDataFetcher hiveDataFetcher = new HiveDataFetcher();
		try {
			hiveDataFetcher.getDataFromHive();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
