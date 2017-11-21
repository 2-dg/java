package db_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelect {
	public static Connection MakeConnection() { // DB연결함수. 필수
		String url = "jdbc:mysql://localhost/book_db";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 1.드라이버 적재
			try {
				con = DriverManager.getConnection(url, "root", "1029");
				System.out.println("###DB 접속 완료###");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("###DB 접속 불가###");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("###드라이버 적재 불가###");
		}
		return con;
	}
	public static void main(String[] args) {
		String name = "김철수";
		String dept = "바뀐학과C";
		Connection c = MakeConnection();
		try {
			Statement stmt = c.createStatement();
			stmt.execute("update student set dept = '"+dept+"' where name = '"+name+"'");
			//insertStudent(c);
			ResultSet rs = stmt.executeQuery("select * from student");
			while(rs.next()) {
				String namep = rs.getString("name");
				String deptp = rs.getString("dept");
				String idp = rs.getString("id");
				System.out.println(namep+"\t"+deptp+"\t"+idp);
			}
		} catch (SQLException e) {
			System.out.println("스테이트먼트 오류");
			e.printStackTrace();
		}
	}
	public static void insertStudent(Connection con) {
			try {
				Statement stmt = con.createStatement();
				for(int i =0;i<3;i++) {
					String id = "1233"+i;
				stmt.executeUpdate("insert into student (name,dept,id) values"
						+"('홍길동','컴퓨터','20170"+i+"')");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}