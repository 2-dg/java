package db_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {
	public static Connection MakeConnection() { // DB�����Լ�. �ʼ�
		String url = "jdbc:mysql://localhost/book_db";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 1.����̹� ����
			try {
				con = DriverManager.getConnection(url, "root", "1029");
				System.out.println("###DB ���� �Ϸ�###");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("###DB ���� �Ұ�###");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("###����̹� ���� �Ұ�###");
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		Connection con = MakeConnection();
		// selectBook(con);
		// insertBook(con, "Artificial Inteligence", "Addison Wesley", "2002",
		// 35000);
		// selectBook(con);
//		insertStudent(con, "�̸�A", "�а�", "10111");
//		insertStudent(con, "�̸�B", "�а�", "10010");
//		insertStudent(con, "�̸�C", "�а�", "10410");
		selectStudnt(con);
		//updateStudentName(con,"�̸�A", "�ٲ��а�");

	}// end of main

	private static void updateStudentName(Connection con, String name, String dept) {
		try {
			System.out.println();
			Statement stmt = con.createStatement();
			//ResultSet rs = 
			stmt.executeUpdate("update student set dept='"+dept+"'where name ='"+name+"'");
			
			System.out.println("###������ ��� �Ϸ�###");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static void selectStudnt(Connection con) {
		try {
			System.out.println();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				String name = rs.getString("name");
				String dept = rs.getString("dept");
				String id = rs.getString("id");
				System.out.println(name + " " + dept + " " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	private static void insertStudent(Connection con, String name, String dept, String id) {
		try {
			Statement stmt = con.createStatement();
			String str = "INSERT INTO student (name, dept, id) VALUES";
			str += "('" + name + "', '" + dept + "', '" + id + "')";
			System.out.println(str);
			int count = stmt.executeUpdate(str);
			if (count == 1) {
				System.out.println("���������� ���ڵ� �Էµ�");
			} else {
				System.out.println("���ڵ� �Է� ����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void insertBook(Connection con, String title, String publisher, String year, int price) {
		try {
			Statement stmt = con.createStatement();
			String str = "INSERT INTO books (title, publisher, year, price) VALUES";
			str += "('" + title + "', '" + publisher + "', '" + year + "'," + price + ")";
			int count = stmt.executeUpdate(str);
			if (count == 1) {
				System.out.println("���������� ���ڵ� �Էµ�");
			} else {
				System.out.println("���ڵ� �Է� ����");
			}

		} catch (SQLException e) {
			System.exit(0);
		}
	}
	private static void selectBook(Connection con) {
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			while (rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");
				Book book = new Book(book_id, title, publisher, year, price);
				list.add(book);
				System.out.println(book_id + " " + title + " " + publisher + " " + year + " " + price);
			}
			System.out.println("###������ ��� �Ϸ�###");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.println("��ü�� ���");
		for (Book book : list) {
			System.out.println(book);
		}
	}
}
