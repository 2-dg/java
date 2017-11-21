package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectDB {
	private static Connection con = makeConnection();
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
			printStudent(con);
			deleteStudent(con);
			
		
	}

	private static void printStudent(Connection con) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while(rs.next()) {
				String name = rs.getString("name");
				String dept = rs.getString("dept");
				String id = rs.getString("id");
				System.out.println("�̸� : "+name+ "\t �а� : "+dept+"\t �й� : "+id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void deleteStudent(Connection con) {
		try {
			int count = 0;
			Statement stmt = con.createStatement();
			System.out.print("������ �̸� �Է� : ");
			String input = scan.nextLine();
			count = stmt.executeUpdate("delete from student where name = '"+input+"'");
			if(count==1) {
				System.out.println("��� ���� �Ϸ�");
			}else {
				System.out.println("��� ���� �Ұ���");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/book_db";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url,"root","1029");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("DB ���� ����");
		return con;
	}

}
