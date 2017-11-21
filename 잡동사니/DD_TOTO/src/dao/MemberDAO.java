package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.GameVO;
import Model.MemberVO;
import application.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MemberDAO {
	
	public MemberVO memberSearch(String search, String comboUserSearch){
		StringBuffer sql = new StringBuffer();
		
		if(comboUserSearch=="아이디"){
			sql.append("select * from dd_joinmember where id like ");
			sql.append("?");
		}else if(comboUserSearch=="이름"){
			sql.append("select * from dd_joinmember where name like ");
			sql.append("?");
		}else if(comboUserSearch=="권한"){
			sql.append("select * from dd_joinmember where userType like ");
			sql.append("?");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		ResultSet rs = null;//레코드를 받아와서 진행해야 되는 객체

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + search + "%");

			rs = pstmt.executeQuery();//executeUpdate()(insert,update,delete)

			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNum1(rs.getString("phoneNum1"));
				memberVO.setPhoneNum2(rs.getString("phoneNum2"));
				memberVO.setPhoneNum3(rs.getString("phoneNum3"));
				memberVO.setBankName(rs.getString("bankName"));
				memberVO.setAccountNum(rs.getString("accountNum"));
				memberVO.setAccountHolder(rs.getString("accountHolder"));
				memberVO.setUserMoney(rs.getString("userMoney"));
				memberVO.setUserType(rs.getString("userType"));
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
		return memberVO;
	}
	
	
	//ID로 회원 검색
	public MemberVO memberSearchId(String id){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_joinmember where id like ");
		sql.append("?");

		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		ResultSet rs = null;//레코드를 받아와서 진행해야 되는 객체

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + id + "%");

			rs = pstmt.executeQuery();//executeUpdate()(insert,update,delete)

			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNum1(rs.getString("phoneNum1"));
				memberVO.setPhoneNum2(rs.getString("phoneNum2"));
				memberVO.setPhoneNum3(rs.getString("phoneNum3"));
				memberVO.setBankName(rs.getString("bankName"));
				memberVO.setAccountNum(rs.getString("accountNum"));
				memberVO.setAccountHolder(rs.getString("accountHolder"));
				memberVO.setUserMoney(rs.getString("userMoney"));
				memberVO.setUserType(rs.getString("userType"));
				
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
		return memberVO;
	}
	
	//이름으로 회원 검색
	public MemberVO memberSearchName(String name){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_joinmember where name like ");
		sql.append("?");

		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		ResultSet rs = null;//레코드를 받아와서 진행해야 되는 객체

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();//executeUpdate()(insert,update,delete)

			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNum1(rs.getString("phoneNum1"));
				memberVO.setPhoneNum2(rs.getString("phoneNum2"));
				memberVO.setPhoneNum3(rs.getString("phoneNum3"));
				memberVO.setBankName(rs.getString("bankName"));
				memberVO.setAccountNum(rs.getString("accountNum"));
				memberVO.setAccountHolder(rs.getString("accountHolder"));
				memberVO.setUserMoney(rs.getString("userMoney"));
				memberVO.setUserType(rs.getString("userType"));
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
		return memberVO;
	}
	
	//선택된 회원 삭제
	public void memberDelete(String id){
		 StringBuffer sql = new StringBuffer();
	      sql.append("delete from dd_joinmember where id = ?");
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         int i = pstmt.executeUpdate();
	         if (i == 1) {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("삭제");
	            alert.setHeaderText("삭제 완료");
	            alert.setContentText("삭제를 성공하였습니다");
	            alert.showAndWait();
	         } else {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("삭제");
	            alert.setHeaderText("삭제 실패");
	            alert.setContentText("삭제를 실패하였습니다");
	            alert.showAndWait();
	         }
	      } catch (SQLException e) {
	         System.out.println("e=[" + e + "]");
	      } catch (Exception e) {
	         System.out.println("e=[" + e + "]");
	      }finally {
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
	
	//회원정보 수정
	public void memberEdit(MemberVO memberVO, String id){
		StringBuffer sql = new StringBuffer();
		sql.append("update dd_joinmember set ");
		sql.append("id = ? , name = ?, phoneNum1 = ?, phoneNum2= ?, phoneNum3=?, bankName = ?,");
		sql.append("accountNum = ?, accountHolder = ?, userType = ?, userMoney = ? ");
		sql.append("where id = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getPhoneNum1());
			pstmt.setString(4, memberVO.getPhoneNum2());
			pstmt.setString(5, memberVO.getPhoneNum3());
			pstmt.setString(6, memberVO.getBankName());
			pstmt.setString(7, memberVO.getAccountNum());
			pstmt.setString(8, memberVO.getAccountHolder());
			pstmt.setString(9, memberVO.getUserType());
			pstmt.setString(10, memberVO.getUserMoney());
			pstmt.setString(11, memberVO.getId());
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("회원 수정");
				alert.setHeaderText("회원 수정 완료");
				alert.setContentText("수정을 완료하였습니다");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("회원 수정");
				alert.setHeaderText("회원 수정 실패.");
				alert.setContentText("수정을 실패하였습니다");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	//베팅한 돈 깎이게
	public void bettedMoney(String id, int userMoney) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update dd_joinmember set ");
		sql.append("usermoney = ? where id = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO game = new GameVO();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, userMoney);
			pstmt.setString(2, id);
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("베팅 완료");
				alert.setHeaderText("베팅 완료");
				alert.setContentText("베팅을 완료하였습니다");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("게임 베팅");
				alert.setHeaderText("게임 베팅 실패.");
				alert.setContentText("베팅에 실패하였습니다");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	//로그아웃하면 상태가 비접속으로 전환
	public MemberVO logoutMemeber(String id) {		
		StringBuffer sql = new StringBuffer();
		sql.append("update dd_joinmember set ");
		sql.append("userStatus = ? where id = ?");
				
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO loginMember = null;
		try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, "비접속");
	         pstmt.setString(2, id);
	         pstmt.executeUpdate();
	      } catch (SQLException se) {
	         System.out.println(se);
	      } catch (Exception e) {
	         System.out.println(e);
	      } finally {
	         try {
	            if (pstmt != null)
	               pstmt.close();
	            if (con != null)
	               con.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
		return loginMember;
	}
	
	//로그인해있는 멤버의 정보를 가져와 객체로 반환
	public MemberVO loginMemeber(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id, name, phoneNum1, phoneNum2, phoneNum3, ");
		sql.append("bankname, accountNum, accountHolder,");
		sql.append("usertype, usermoney, userStatus from dd_joinmember where id = ?");
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append("update dd_joinmember set ");
		sql2.append("userStatus = ? where id = ?");
				
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		MemberVO loginMember = null;
		try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         pstmt2 = con.prepareStatement(sql2.toString());
	         pstmt2.setString(1, "접속중");
	         pstmt2.setString(2, id);
	         rs = pstmt.executeQuery();
	         pstmt2.executeUpdate();
	         while (rs.next()) {
	            loginMember = new MemberVO();
	            loginMember.setId(rs.getString("id"));
	            loginMember.setName(rs.getString("name"));
	            loginMember.setPhoneNum1(rs.getString("phoneNum1"));
	            loginMember.setPhoneNum2(rs.getString("phoneNum2"));
	            loginMember.setPhoneNum3(rs.getString("phoneNum3"));
	            loginMember.setBankName(rs.getString("bankname"));
	            loginMember.setAccountNum(rs.getString("accountNum"));
	            loginMember.setAccountHolder(rs.getString("accountHolder"));
	            loginMember.setUserType(rs.getString("usertype"));
	            loginMember.setUserMoney(rs.getString("usermoney"));
	            loginMember.setUserStatus(rs.getString("userStatus"));
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
	            se.printStackTrace();
	         }
	      }
		return loginMember;
	}
	
	public ArrayList<MemberVO> getMemberTotal() {
	      ArrayList<MemberVO> list = new ArrayList<MemberVO>();
	      StringBuffer sql = new StringBuffer();
	      sql.append("select id, name, phoneNum1, phoneNum2, phoneNum3, "
	            + "bankname, accountNum, accountHolder, usertype, usermoney, "
	            + "userStatus from dd_joinmember");

	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      MemberVO joinMember = null;

	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql.toString());
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            joinMember = new MemberVO();
	            joinMember.setId(rs.getString("id"));
	            joinMember.setName(rs.getString("name"));
	            joinMember.setPhoneNum1(rs.getString("phoneNum1"));
	            joinMember.setPhoneNum2(rs.getString("phoneNum2"));
	            joinMember.setPhoneNum3(rs.getString("phoneNum3"));
	            String pn1 = rs.getString("phoneNum1");
	            String pn2 = rs.getString("phoneNum2");
	            String pn3 = rs.getString("phoneNum3");
	            joinMember.setPhoneNumber(pn1+"-"+pn2+"-"+pn3);
	            joinMember.setBankName(rs.getString("bankname"));
	            joinMember.setAccountNum(rs.getString("accountNum"));
	            joinMember.setAccountHolder(rs.getString("accountHolder"));
	            joinMember.setUserType(rs.getString("usertype"));
	            joinMember.setUserMoney(rs.getString("usermoney"));
	            joinMember.setUserStatus(rs.getString("userStatus"));
	            list.add(joinMember);
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
	
	public MemberVO joinMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into dd_joinmember");
		sql.append("(id, name, pw1, pw2, phoneNum1, phoneNum2, phoneNum3,");
		sql.append("bankName, accountNum, accountHolder, userMoney)");
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberthis = member;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memberthis.getId());
			pstmt.setString(2, memberthis.getName());
			pstmt.setString(3, memberthis.getPw1());
			pstmt.setString(4, memberthis.getPw2());
			pstmt.setString(5, memberthis.getPhoneNum1());
			pstmt.setString(6, memberthis.getPhoneNum2());
			pstmt.setString(7, memberthis.getPhoneNum3());
			pstmt.setString(8, memberthis.getBankName());
			pstmt.setString(9, memberthis.getAccountNum());
			pstmt.setString(10, memberthis.getAccountHolder());
			pstmt.setString(11, memberthis.getUserMoney());
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
		return memberthis;
	}

	public void idOverlap(MemberVO member) {
		Connection con;
		try {
			con = DBUtil.getConnection();
			PreparedStatement ps = null;
			String sql = "select id from dd_joinmember where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getId());
			ResultSet rs = ps.executeQuery();
			if (member.getId().equals("")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 공란");
				alert.setHeaderText("중복검사할 아이디 입력");
				alert.setContentText("아이디를 입력하시오");
				alert.showAndWait();
			}else if (rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복");
				alert.setHeaderText("아이디가 중복되었습니다.");
				alert.setContentText("다른 아이디를 입력하시오");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 유효");
				alert.setHeaderText("사용 가능한 아이디입니다.");
				alert.setContentText("생성 가능한 아이디");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}
}
