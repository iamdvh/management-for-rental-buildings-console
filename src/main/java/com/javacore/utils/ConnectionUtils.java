package com.javacore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static String db_url = "jdbc:mysql://localhost:3306/estatebasic";
	private static String user = "root";
	private static String password = "mavryk";
	public static Connection getConnection() {
		Connection con = null;
	    try {
			con = DriverManager.getConnection(db_url, user, password);
			return con;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
