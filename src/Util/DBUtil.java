package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static String ip = "127.0.0.1";
	public  static int port = 3306;
	public static String database = "hutubill";
	public static String encoding = "UTF-8";
	public static String loginName = "root";
	public static String password = "jatqq447xxwwkk";
	
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
