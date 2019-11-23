package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static String ip = "127.0.0.1";
	private static int port = 3306;
	private static String database = "hutubill";
	private static String encoding = "UTF-8";
	private static String loginName = "root";
	private static String password = "jatqq447xxwwkk";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s ? characterEncoding = %s & serverTimezone = GMT", ip,
				port,database,encoding);
		return DriverManager.getConnection(url,loginName,password);
	}

}
