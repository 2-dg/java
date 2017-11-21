package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static final String driver = "com.mysql.jdbc.Driver";
//	static final String url = "jdbc:mysql://192.168.0.49/book_db";
	static final String url = "jdbc:mysql://localhost/book_db";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url, "aaaa", "123456");
		Connection con = DriverManager.getConnection(url, "root", "123456");
		System.out.println("DB 연결 성공");
		return con;
	}
}

