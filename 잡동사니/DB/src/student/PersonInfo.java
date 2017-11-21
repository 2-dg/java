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
		System.out.println("DB 접속 성공");
		return con;
	}

	public static void main(String[] args) {
		try {
			Statement stmt = con.createStatement();
			System.out.print("삽입 데이터 갯수를 입력 : ");
			int count = Integer.parseInt(scan.nextLine());
			for (int i = 0; i < count; i++) {
				System.out.print((i + 1) + "번째 정보 입력입니다. 이름을 입력하세요 : ");
				String name = scan.nextLine();
				System.out.print("전화번호를 입력하세요 : ");
				String pnum = scan.nextLine();
				System.out.print("메일을 입력하세요 : ");
				String mail = scan.nextLine();
				int check = stmt.executeUpdate("insert into person (name, pnum, mail) values('" + name + "'" + ",'" + pnum + "','"
						+ mail + "')");
				if (check==1) {
					System.out.println("데이터 입력 성공");
				}
			}
			System.out.println("DB정보를 출력합니다.");
			System.out.println("INDEX \t이름 \t전화번호 \t이메일");
			ResultSet rs = stmt.executeQuery("select * from person");
			while(rs.next()) {
				int num = rs.getInt("num");
				String namep = rs.getString("name");
				String pnump = rs.getString("pnum");
				String mailp = rs.getString("mail");
				System.out.println(num+" \t"+namep+" \t"+pnump+" \t"+mailp);
			}
			System.out.print("전화번호를 출력할 인덱스를 입력하십시오 : ");
			int index = Integer.parseInt(scan.nextLine());
			ResultSet rs2 = stmt.executeQuery("select pnum from person where num ="+index);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
