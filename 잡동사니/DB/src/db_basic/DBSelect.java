package db_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelect {
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
	public static void main(String[] args) {
		String name = "��ö��";
		String dept = "�ٲ��а�C";
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
			System.out.println("������Ʈ��Ʈ ����");
			e.printStackTrace();
		}
	}
	public static void insertStudent(Connection con) {
			try {
				Statement stmt = con.createStatement();
				for(int i =0;i<3;i++) {
					String id = "1233"+i;
				stmt.executeUpdate("insert into student (name,dept,id) values"
						+"('ȫ�浿','��ǻ��','20170"+i+"')");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}