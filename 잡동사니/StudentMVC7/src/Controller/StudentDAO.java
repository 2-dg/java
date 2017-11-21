package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.StudentVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StudentDAO {
	// �� �ű� �л� ���� ���
	public StudentVO getStudentregiste(StudentVO svo) throws Exception {
		// �� ������ ó���� ���� SQL ��  now() --> sysdate(����Ŭ)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into schoolchild ");
		sql.append("(name, year, ban, gender, korean, english, math, sic, soc, music, total, avg, register, filename)");
		sql.append(" values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?)");

		Connection con = null;
		//DB�� ���ӿ�û
		PreparedStatement pstmt = null;
		//������ ����
		StudentVO sVo = svo;
		//DB���� ������ �����͸� ������ ��ü

		try {
			// �� DBUtil�̶�� Ŭ������ getConnection( )�޼���� �����ͺ��̽��� ����(��������Լ�)
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// �� �Է¹��� �л� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sVo.getName());
			pstmt.setString(2, sVo.getYear());
			pstmt.setString(3, sVo.getBan());
			pstmt.setString(4, sVo.getGender());
			
			pstmt.setInt(5, sVo.getKorean());
			pstmt.setInt(6, sVo.getEnglish());
			pstmt.setInt(7, sVo.getMath());
			pstmt.setInt(8, sVo.getSic());
			pstmt.setInt(9, sVo.getSoc());
			pstmt.setInt(10, sVo.getMusic());
			pstmt.setInt(11, sVo.getTotal());
			
			pstmt.setDouble(12, sVo.getAvg());
			
			pstmt.setString(13, sVo.getFilename());

			// �� SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return sVo;
	}

	// �� �л��� name�� �Է¹޾� ���� ��ȸ
	public StudentVO getStudentCheck(String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from schoolchild where name like ");
		sql.append("? order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	//���ڵ带 �޾ƿͼ� �����ؾ� �ϴ� ��ü
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sVo = new StudentVO();
				sVo.setNo(rs.getInt("no"));
				sVo.setName(rs.getString("name"));
				sVo.setYear(rs.getString("year"));
				sVo.setBan(rs.getString("ban"));
				sVo.setGender(rs.getString("gender"));
				sVo.setKorean(rs.getInt("korean"));
				sVo.setEnglish(rs.getInt("english"));
				sVo.setMath(rs.getInt("math"));
				sVo.setSic(rs.getInt("sic"));
				sVo.setSoc(rs.getInt("soc"));
				sVo.setMusic(rs.getInt("music"));
				sVo.setTotal(rs.getInt("total"));
				sVo.setAvg(rs.getDouble("avg"));
				sVo.setRegister(rs.getString("register"));
				sVo.setFilename(rs.getString("filename"));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return sVo;
	}

	// ������ �л��� ���� ����
	public StudentVO getStudentUpdate(StudentVO svo, int no) throws Exception {
		// �� ������ ó���� ���� SQL ��
		StringBuffer sql = new StringBuffer();
		sql.append("update schoolchild set ");
		sql.append(" korean=?, english=?, math=?, sic=?, soc=?, music=?, total=?, avg=? ");
		sql.append(" where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		StudentVO retval = null;

		try {
			// �� DBUtil�̶�� Ŭ������ getConnection( )�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �� ������ �л� ������ �����ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, svo.getKorean());
			pstmt.setInt(2, svo.getEnglish());
			pstmt.setInt(3, svo.getMath());
			pstmt.setInt(4, svo.getSic());
			pstmt.setInt(5, svo.getSoc());
			pstmt.setInt(6, svo.getMusic());
			pstmt.setInt(7, svo.getTotal());
			pstmt.setDouble(8, svo.getAvg());
			pstmt.setDouble(9, no);

			// �� SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� �Ϸ�.");
				alert.setContentText("���� ���� ����!!!");

				alert.showAndWait();
				retval = new StudentVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� ����.");
				alert.setContentText("���� ���� ����!!!");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return retval;
	}

	public void getStudentDelete(int no) throws Exception {
		// �� ������ ó���� ���� SQL ��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from schoolchild where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// �� DBUtil�̶�� Ŭ������ getConnection( )�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �� SQL���� ������ ó�� ����� ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// �� SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� ����");
				alert.setHeaderText("�л� ���� �Ϸ�.");
				alert.setContentText("�л� ���� ����!!!");

				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� ����");
				alert.setHeaderText("�л� ���� ����.");
				alert.setContentText("�л� ���� ����!!!");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

	}

	// �л� ��ü ����Ʈ
	public ArrayList<StudentVO> getStudentTotal() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select no, name, year, ban, gender, korean, ");
		sql.append("english, math, sic, soc, music, total, avg, ");
		sql.append("register, filename from schoolchild order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new StudentVO();
				sVo.setNo(rs.getInt("no"));
				sVo.setName(rs.getString("name"));
				sVo.setYear(rs.getString("year"));
				sVo.setBan(rs.getString("ban"));
				sVo.setGender(rs.getString("gender"));
				sVo.setKorean(rs.getInt("korean"));
				sVo.setEnglish(rs.getInt("english"));
				sVo.setMath(rs.getInt("math"));
				sVo.setSic(rs.getInt("sic"));
				sVo.setSoc(rs.getInt("soc"));
				sVo.setMusic(rs.getInt("music"));
				sVo.setTotal(rs.getInt("total"));
				sVo.setAvg(rs.getDouble("avg"));
				sVo.setRegister(rs.getDate("register")+"");
				sVo.setFilename(rs.getString("filename"));			 

				list.add(sVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}

	// �����ͺ��̽����� �л� ���̺��� �÷��� ����
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from schoolchild");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData ��ü ���� ����
		ResultSetMetaData rsmd = null;
		//���̺� �Ӽ��� ������.
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();//���̺��� �÷����� �����´�. 
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return columnName;
	}
}