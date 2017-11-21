package db_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/book_db";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,"root","1029");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("명령어");
		//executeQuery는 자바에서 실행
		while(rs.next()) {
		}
		stmt.execute("명령어");
		//execute는 MySQL에서 실행
	}
}