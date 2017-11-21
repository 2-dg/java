package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PersonInfo {
	private static Connection con = makeConnection();
	private static Scanner scan = new Scanner(System.in);

	private static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/book_db";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url, "root", "1029");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("DB ���� ����");
		return con;
	}

	public static void main(String[] args) {
		try {
			Statement stmt = con.createStatement();
			System.out.print("���� ������ ������ �Է� : ");
			int count = Integer.parseInt(scan.nextLine());
			for (int i = 0; i < count; i++) {
				System.out.print((i + 1) + "��° ���� �Է��Դϴ�. �̸��� �Է��ϼ��� : ");
				String name = scan.nextLine();
				System.out.print("��ȭ��ȣ�� �Է��ϼ��� : ");
				String pnum = scan.nextLine();
				System.out.print("������ �Է��ϼ��� : ");
				String mail = scan.nextLine();
				int check = stmt.executeUpdate("insert into person (name, pnum, mail) values('" + name + "'" + ",'" + pnum + "','"
						+ mail + "')");
				if (check==1) {
					System.out.println("������ �Է� ����");
				}
			}
			System.out.println("DB������ ����մϴ�.");
			System.out.println("INDEX \t�̸� \t��ȭ��ȣ \t�̸���");
			ResultSet rs = stmt.executeQuery("select * from person");
			while(rs.next()) {
				int num = rs.getInt("num");
				String namep = rs.getString("name");
				String pnump = rs.getString("pnum");
				String mailp = rs.getString("mail");
				System.out.println(num+" \t"+namep+" \t"+pnump+" \t"+mailp);
			}
			System.out.print("��ȭ��ȣ�� ����� �ε����� �Է��Ͻʽÿ� : ");
			int index = Integer.parseInt(scan.nextLine());
			ResultSet rs2 = stmt.executeQuery("select pnum from person where num ="+index);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
