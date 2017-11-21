package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameDB {
	
	//�������
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
	            alert.setTitle("����");
	            alert.setHeaderText("���� �Ϸ�");
	            alert.setContentText("������ �����Ͽ����ϴ�");
	            alert.showAndWait();
	         } else {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("����");
	            alert.setHeaderText("���� ����");
	            alert.setContentText("������ �����Ͽ����ϴ�");
	            alert.showAndWait();
	         }
	      } catch (SQLException e) {
	         System.out.println("e=[" + e + "]");
	      } catch (Exception e) {
	         System.out.println("e=[" + e + "]");
	      }finally {
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
	//���Ӽ���
	public void gameEdit(Game game, int index) {
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
			pstmt.setInt(1, game.getGameNo());
			pstmt.setString(2, game.getDate());
			pstmt.setString(3, game.getGame());
			pstmt.setString(4, game.getLeague());
			pstmt.setString(5, game.getHomeTeam());
			pstmt.setString(6, game.getAwayTeam());
			pstmt.setDouble(7, game.getIncomeRate());
			pstmt.setDouble(8, game.getWinRate());
			pstmt.setInt(9, game.getWinMoney());
			pstmt.setDouble(10, game.getDrawRate());
			pstmt.setInt(11, game.getDrawMoney());
			pstmt.setDouble(12, game.getLoseRate());
			pstmt.setInt(13, game.getLoseMoney());
			pstmt.setString(14, game.getResult());
			pstmt.setString(15, game.getBetStatus());
			pstmt.setInt(16, game.getGameNo());
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� �Ϸ�");
				alert.setContentText("������ �Ϸ��Ͽ����ϴ�");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� ����.");
				alert.setContentText("������ �����Ͽ����ϴ�");
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
	//���ӵ��
	public Game gameRegist(Game game) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into dd_toto");
		sql.append("(date, game, league, hometeam, awayteam, incomerate, winrate, winmoney,");
		sql.append("drawrate, drawmoney, loserate, losemoney, result, betstatus)");
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Game gamethis = game;
		
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
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return gamethis;
	}
	//����� ���� �Ӽ��� ����
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_toto");
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
	//��ü ���
	public ArrayList<Game> getGameTotal() {
		ArrayList<Game> list = new ArrayList<Game>();
		StringBuffer sql = new StringBuffer();
		sql.append("select gameno, date, game, league, hometeam, awayteam, incomerate,");
		sql.append("winrate, winmoney, drawrate, drawmoney, loserate, losemoney, result,");
		sql.append("betstatus from dd_toto ");
		sql.append("order by date asc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Game game = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				game = new Game();
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
				list.add(game);
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