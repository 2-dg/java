package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BettingVO;
import Model.GameVO;
import Model.MemberVO;
import application.DBUtil;

public class BettingDAO {
	
	public GameVO betRegist(GameVO gameVO, MemberVO memberVO, String hit, int money, double wr, double dr, double lr) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into dd_betting");
		sql.append("(id, gameDate, game, league, homeTeam, awayTeam, winRate, winMoney, ");
		sql.append("drawRate, drawMoney, loseRate, loseMoney, betPart, bettedMoney)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		GameVO game = gameVO;
		MemberVO member = memberVO;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, game.getDate());
			pstmt.setString(3, game.getGame());
			pstmt.setString(4, game.getLeague());
			pstmt.setString(5, game.getHomeTeam());
			pstmt.setString(6, game.getAwayTeam());
			pstmt.setDouble(7, wr);
			pstmt.setInt(8, game.getWinMoney());
			pstmt.setDouble(9, dr);
			pstmt.setInt(10, game.getDrawMoney());
			pstmt.setDouble(11, lr);
			pstmt.setInt(12, game.getLoseMoney());
			pstmt.setString(13, hit);
			pstmt.setInt(14, money);
			
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
		return game;
	}

	public ArrayList<BettingVO> getBettingTotal() {
		
		ArrayList<BettingVO> list = new ArrayList<BettingVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_betting");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BettingVO betVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				betVO = new BettingVO();
				betVO.setBetNo(rs.getInt("betNo"));
				betVO.setId(rs.getString("id"));
				betVO.setGameDate(rs.getString("gameDate"));
				betVO.setGame(rs.getString("game"));
				betVO.setLeague(rs.getString("league"));
				betVO.setHomeTeam(rs.getString("homeTeam"));
				betVO.setAwayTeam(rs.getString("awayTeam"));
				betVO.setWinRate(rs.getDouble("winRate"));
				betVO.setWinMoney(rs.getInt("winMoney"));
				betVO.setDrawRate(rs.getDouble("drawRate"));
				betVO.setDrawMoney(rs.getInt("drawMoney"));
				betVO.setLoseRate(rs.getDouble("loseRate"));
				betVO.setLoseMoney(rs.getInt("loseMoney"));
				betVO.setBetPart(rs.getString("betPart"));
				betVO.setBettedMoney(rs.getInt("bettedMoney"));
				betVO.setGameResult(rs.getString("gameResult"));
				betVO.setHit(rs.getString("hit"));
				betVO.setPay(rs.getString("pay"));
				list.add(betVO);
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
	
	
	public ArrayList<BettingVO> getPersnalBettingTotal(String id) {
		
		ArrayList<BettingVO> list = new ArrayList<BettingVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_betting where id = ? order by gamedate asc");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BettingVO betVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				betVO = new BettingVO();
				betVO.setBetNo(rs.getInt("betNo"));
				betVO.setId(rs.getString("id"));
				betVO.setGameDate(rs.getString("gameDate"));
				betVO.setGame(rs.getString("game"));
				betVO.setLeague(rs.getString("league"));
				betVO.setHomeTeam(rs.getString("homeTeam"));
				betVO.setAwayTeam(rs.getString("awayTeam"));
				betVO.setWinRate(rs.getDouble("winRate"));
				betVO.setWinMoney(rs.getInt("winMoney"));
				betVO.setDrawRate(rs.getDouble("drawRate"));
				betVO.setDrawMoney(rs.getInt("drawMoney"));
				betVO.setLoseRate(rs.getDouble("loseRate"));
				betVO.setLoseMoney(rs.getInt("loseMoney"));
				betVO.setBetPart(rs.getString("betPart"));
				betVO.setBettedMoney(rs.getInt("bettedMoney"));
				betVO.setGameResult(rs.getString("gameResult"));
				betVO.setHit(rs.getString("hit"));
				betVO.setPay(rs.getString("pay"));
				list.add(betVO);
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

	public ArrayList<BettingVO> getBettinguser(GameVO game) {
		ArrayList<BettingVO> list = new ArrayList<BettingVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dd_betting where game=? and league=? and homeTeam=? and awayTeam=?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BettingVO betVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, game.getGame());
			pstmt.setString(2, game.getLeague());
			pstmt.setString(3, game.getHomeTeam());
			pstmt.setString(4, game.getAwayTeam());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				betVO = new BettingVO();
				betVO.setBetNo(rs.getInt("betNo"));
				betVO.setId(rs.getString("id"));
				betVO.setGameDate(rs.getString("gameDate"));
				betVO.setGame(rs.getString("game"));
				betVO.setLeague(rs.getString("league"));
				betVO.setHomeTeam(rs.getString("homeTeam"));
				betVO.setAwayTeam(rs.getString("awayTeam"));
				betVO.setWinRate(rs.getDouble("winRate"));
				betVO.setWinMoney(rs.getInt("winMoney"));
				betVO.setDrawRate(rs.getDouble("drawRate"));
				betVO.setDrawMoney(rs.getInt("drawMoney"));
				betVO.setLoseRate(rs.getDouble("loseRate"));
				betVO.setLoseMoney(rs.getInt("loseMoney"));
				betVO.setBetPart(rs.getString("betPart"));
				betVO.setBettedMoney(rs.getInt("bettedMoney"));
				betVO.setGameResult(rs.getString("gameResult"));
				betVO.setHit(rs.getString("hit"));
				betVO.setPay(rs.getString("pay"));
				list.add(betVO);
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