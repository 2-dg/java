package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://192.168.0.49/book_db";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "aaaa", "123456");
		return con;
	}
}