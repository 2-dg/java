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
	// ① 신규 학생 점수 등록
	public StudentVO getStudentregiste(StudentVO svo) throws Exception {
		// ② 데이터 처리를 위한 SQL 문  now() --> sysdate(오라클)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into schoolchild ");
		sql.append("(name, year, ban, gender, korean, english, math, sic, soc, music, total, avg, register, filename)");
		sql.append(" values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?)");

		Connection con = null;
		//DB에 접속요청
		PreparedStatement pstmt = null;
		//쿼리문 실행
		StudentVO sVo = svo;
		//DB에서 가져온 데이터를 저장할 객체

		try {
			// ③ DBUtil이라는 클래스의 getConnection( )메서드로 데이터베이스와 연결(정적멤버함수)
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// ④ 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
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

			// ⑤ SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return sVo;
	}

	// ⑦ 학생의 name을 입력받아 정보 조회
	public StudentVO getStudentCheck(String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from schoolchild where name like ");
		sql.append("? order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	//레코드를 받아와서 진행해야 하는 개체
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

	// 선택한 학생의 점수 수정
	public StudentVO getStudentUpdate(StudentVO svo, int no) throws Exception {
		// ② 데이터 처리를 위한 SQL 문
		StringBuffer sql = new StringBuffer();
		sql.append("update schoolchild set ");
		sql.append(" korean=?, english=?, math=?, sic=?, soc=?, music=?, total=?, avg=? ");
		sql.append(" where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		StudentVO retval = null;

		try {
			// ③ DBUtil이라는 클래스의 getConnection( )메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// ④ 수정한 학생 정보를 수정하기 위하여 SQL문장을 생성
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

			// ⑤ SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("점수 수정");
				alert.setHeaderText("점수 수정 완료.");
				alert.setContentText("점수 수정 성공!!!");

				alert.showAndWait();
				retval = new StudentVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("점수 수정");
				alert.setHeaderText("점수 수정 실패.");
				alert.setContentText("점수 수정 실패!!!");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
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
		// ② 데이터 처리를 위한 SQL 문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from schoolchild where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// ③ DBUtil이라는 클래스의 getConnection( )메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// ⑤ SQL문을 수행후 처리 결과를 얻어옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// ⑤ SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 완료.");
				alert.setContentText("학생 삭제 성공!!!");

				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 실패.");
				alert.setContentText("학생 삭제 실패!!!");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

	}

	// 학생 전체 리스트
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

	// 데이터베이스에서 학생 테이블의 컬럼의 갯수
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from schoolchild");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;
		//테이블 속성명 가져옴.
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();//테이블의 컬럼수를 가져온다. 
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