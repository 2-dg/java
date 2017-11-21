package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.GameVO;
import application.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameDAO {
	//게임 정보 찾기 기능
	public GameVO gameSearch(String search, String comboGMsearch){
		StringBuffer sql = new StringBuffer();
		
		if(comboGMsearch=="경기 날짜"){
			sql.append("select * from dd_toto where date like ");
			sql.append("?");
		}else if(comboGMsearch=="경기 종목"){
			sql.append("select * from dd_toto where game like ");
			sql.append("?");
		}else if(comboGMsearch=="리그명"){
			sql.append("select * from dd_toto where league like ");
			sql.append("?");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO gameVO = null;
		ResultSet rs = null;//레코드를 받아와서 진행해야 되는 객체

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + search + "%");

			rs = pstmt.executeQuery();//executeUpdate()(insert,update,delete)

			while (rs.next()) {
				gameVO = new GameVO();
				gameVO.setDate(rs.getString("date"));
				gameVO.setGame(rs.getString("game"));
				gameVO.setLeague(rs.getString("league"));
				gameVO.setHomeTeam(rs.getString("homeTeam"));
				gameVO.setAwayTeam(rs.getString("awayTeam"));
				gameVO.setIncomeRate(rs.getInt("incomeRate"));
				gameVO.setWinRate(rs.getInt("winRate"));
				gameVO.setLoseRate(rs.getInt("loseRate"));
				gameVO.setBetStatus(rs.getString("betStatus"));
				gameVO.setResult(rs.getString("result"));
			
				
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
		return gameVO;
	}
	//게임 날짜로 찾기 기능
	public GameVO gameSearchDate(String searchDate){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_toto where date like ");
		sql.append("?");

		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO gameVO = null;
		ResultSet rs = null;//레코드를 받아와서 진행해야 되는 객체

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchDate + "%");

			rs = pstmt.executeQuery();//executeUpdate()(insert,update,delete)

			if (rs.next()) {
				gameVO = new GameVO();
				gameVO.setDate(rs.getString("date"));
				gameVO.setGame(rs.getString("game"));
				gameVO.setLeague(rs.getString("league"));
				gameVO.setHomeTeam(rs.getString("homeTeam"));
				gameVO.setAwayTeam(rs.getString("awayTeam"));
				gameVO.setIncomeRate(rs.getInt("incomeRate"));
				gameVO.setWinRate(rs.getInt("winRate"));
				gameVO.setLoseRate(rs.getInt("loseRate"));
				gameVO.setBetStatus(rs.getString("betStatus"));
				gameVO.setResult(rs.getString("result"));
			
				
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
		return gameVO;
	}
	//게임 베팅기능
	public int gameBetting(int index, int money, String betType, double wr, double dr, double lr) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update dd_toto set ");
		if(betType.equals("승")) {
			sql.append("winmoney = ?, winRate = ?, drawRate = ?, loseRate = ? ");			
		}else if(betType.equals("무")) {
			sql.append("drawmoney = ?, winRate = ?, drawRate = ?, loseRate = ? ");
		}else if(betType.equals("패")) {
			sql.append("losemoney = ?, winRate = ?, drawRate = ?, loseRate = ? ");
		}
		sql.append("where gameno = ?");
			
		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO game = new GameVO();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, money);
			pstmt.setDouble(2, wr);
			pstmt.setDouble(3, dr);
			pstmt.setDouble(4, lr);
			pstmt.setInt(5, index);
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("베팅 완료");
				alert.setHeaderText("베팅 완료");
				alert.setContentText("베팅을 완료하였습니다");
				alert.showAndWait();
				return 1;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("게임 베팅");
				alert.setHeaderText("게임 베팅 실패.");
				alert.setContentText("베팅에 실패하였습니다");
				alert.showAndWait();
				return 0;
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
		return 0;		
	}	
	//게임 선택기능
	public GameVO gameSelect(int index) {
		StringBuffer sql = new StringBuffer();
		sql.append("select gameno, date, game, league, hometeam, awayteam, incomerate,");
		sql.append("winrate, winmoney, drawrate, drawmoney, loserate, losemoney, result,");
		sql.append("betstatus from dd_toto where gameno=?");

		GameVO game = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				game = new GameVO();
				game.setGameNo(rs.getInt("gameno"));
				game.setDate(rs.getString("date"));
				game.setGame(rs.getString("game"));
				game.setLeague(rs.getString("league"));
				game.setHomeTeam(rs.getString("hometeam"));
				game.setAwayTeam(rs.getString("awayteam"));
				game.setIncomeRate(rs.getDouble("incomerate"));
				game.setWinRate(rs.getDouble("winrate"));
				game.setWinMoney(rs.getInt("winmoney"));
				game.setDrawRate(rs.getDouble("drawrate"));
				game.setDrawMoney(rs.getInt("drawmoney"));
				game.setLoseRate(rs.getDouble("loserate"));
				game.setLoseMoney(rs.getInt("losemoney"));
				game.setResult(rs.getString("result"));
				game.setBetStatus(rs.getString("betstatus"));
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
		return game;
	}
	//삭제기능
	public void gameDelete(int index){
	      StringBuffer sql = new StringBuffer();
	      sql.append("delete from dd_toto where gameNo = ?");
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setInt(1, index);
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
	//게임수정
	public void gameEdit(GameVO gameVO, int index) {
		StringBuffer sql = new StringBuffer();
		sql.append("update dd_toto set ");
		sql.append("gameno = ? , date = ?, game = ?, league = ?, hometeam = ?, awayteam = ?,");
		sql.append("incomerate = ?, winrate = ?, winmoney = ?, drawrate = ?, drawmoney = ?,");
		sql.append("loserate = ?, losemoney = ?, result = ?, betstatus = ?");
		sql.append("where gameno = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, gameVO.getGameNo());
			pstmt.setString(2, gameVO.getDate());
			pstmt.setString(3, gameVO.getGame());
			pstmt.setString(4, gameVO.getLeague());
			pstmt.setString(5, gameVO.getHomeTeam());
			pstmt.setString(6, gameVO.getAwayTeam());
			pstmt.setDouble(7, gameVO.getIncomeRate());
			pstmt.setDouble(8, gameVO.getWinRate());
			pstmt.setInt(9, gameVO.getWinMoney());
			pstmt.setDouble(10, gameVO.getDrawRate());
			pstmt.setInt(11, gameVO.getDrawMoney());
			pstmt.setDouble(12, gameVO.getLoseRate());
			pstmt.setInt(13, gameVO.getLoseMoney());
			pstmt.setString(14, gameVO.getResult());
			pstmt.setString(15, gameVO.getBetStatus());
			pstmt.setInt(16, gameVO.getGameNo());
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("게임 수정");
				alert.setHeaderText("게임 수정 완료");
				alert.setContentText("수정을 완료하였습니다");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("게임 수정");
				alert.setHeaderText("게임 수정 실패.");
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
	//게임등록
	public GameVO gameRegist(GameVO gameVO) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into dd_toto");
		sql.append("(date, game, league, hometeam, awayteam, incomerate, winrate, winmoney,");
		sql.append("drawrate, drawmoney, loserate, losemoney, result, betstatus)");
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO gamethis = gameVO;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, gamethis.getDate());
			pstmt.setString(2, gamethis.getGame());
			pstmt.setString(3, gamethis.getLeague());
			pstmt.setString(4, gamethis.getHomeTeam());
			pstmt.setString(5, gamethis.getAwayTeam());
			pstmt.setDouble(6, gamethis.getIncomeRate());
			pstmt.setDouble(7, gamethis.getWinRate());
			pstmt.setInt(8, gamethis.getWinMoney());
			pstmt.setDouble(9,gamethis.getDrawRate());
			pstmt.setInt(10, gamethis.getDrawMoney());
			pstmt.setDouble(11, gamethis.getLoseRate());
			pstmt.setInt(12, gamethis.getLoseMoney());
			pstmt.setString(13, gamethis.getResult());
			pstmt.setString(14, gamethis.getBetStatus());
			
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
		return gamethis;
	}
	//출력을 위한 속성명 추출
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_toto");
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
	//전체 출력
	public ArrayList<GameVO> getGameTotal() {
		ArrayList<GameVO> list = new ArrayList<GameVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select gameno, date, game, league, hometeam, awayteam, incomerate,");
		sql.append("winrate, winmoney, drawrate, drawmoney, loserate, losemoney, result,");
		sql.append("betstatus from dd_toto ");
		sql.append("order by date asc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GameVO gameVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gameVO = new GameVO();
				gameVO.setGameNo(rs.getInt("gameno"));
				gameVO.setDate(rs.getString("date"));
				gameVO.setGame(rs.getString("game"));
				gameVO.setLeague(rs.getString("league"));
				gameVO.setHomeTeam(rs.getString("hometeam"));
				gameVO.setAwayTeam(rs.getString("awayteam"));
				gameVO.setIncomeRate(rs.getDouble("incomerate"));
				gameVO.setWinRate(rs.getDouble("winrate"));
				gameVO.setWinMoney(rs.getInt("winmoney"));
				gameVO.setDrawRate(rs.getDouble("drawrate"));
				gameVO.setDrawMoney(rs.getInt("drawmoney"));
				gameVO.setLoseRate(rs.getDouble("loserate"));
				gameVO.setLoseMoney(rs.getInt("losemoney"));
				gameVO.setResult(rs.getString("result"));
				gameVO.setBetStatus(rs.getString("betstatus"));
				list.add(gameVO);
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
}