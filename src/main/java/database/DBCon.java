package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	private static Connection connection = null;
	private static String url = "jdbc:sqlserver://";
	private static String serverName = "DESKTOP-VL7UGOA";
	private static String portNumber = "1433";
	private static String databaseName = "apartment_manager";
	private static String userName = "sa";
	private static String password = "123";
	
	public static String getUrl() {
		// truoc 9.4.1
		return url + serverName + ":" 
				+ portNumber + "; databaseName=" + databaseName 
				+ "; user=" + userName + "; password=" + password;
		// sau 9.4.1
		// + "; Encrypt=true; trustServerCertificate=true";
	}
	
	public static Connection getConn() {
		try {
			connection = DriverManager.getConnection(getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			connection = null;
		}
		
		return connection;
	}
}