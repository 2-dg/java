package Model;

import java.util.Date;

public class Game {
	private Date date;			//경기날짜
	private String game;		//경기종류
	private String laegueName;	//리그종류

	private String homeTeamName;//홈팀이름
	private String awayTeamName;//어웨이팀 이름
	
	private double incomeRate;	//관리자 수익률
	private double winRate;		//홈팀 승리시 배당률
	private double drawRate;	//무승부시 배당률
	private double loseRate;	//홈팀 패배시 배당률
	
	private boolean betStatus;	//배팅 상태 설정

	public Game(Date date, String game, String laegueName, String homeTeamName, String awayTeamName, double incomeRate,
			double winRate, double drawRate, double loseRate, boolean betStatus) {
		super();
		this.date = date;
		this.game = game;
		this.laegueName = laegueName;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.incomeRate = incomeRate;
		this.winRate = winRate;
		this.drawRate = drawRate;
		this.loseRate = loseRate;
		this.betStatus = betStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getLaegueName() {
		return laegueName;
	}

	public void setLaegueName(String laegueName) {
		this.laegueName = laegueName;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public double getIncomeRate() {
		return incomeRate;
	}

	public void setIncomeRate(double incomeRate) {
		this.incomeRate = incomeRate;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getDrawRate() {
		return drawRate;
	}

	public void setDrawRate(double drawRate) {
		this.drawRate = drawRate;
	}

	public double getLoseRate() {
		return loseRate;
	}

	public void setLoseRate(double loseRate) {
		this.loseRate = loseRate;
	}

	public boolean isBetStatus() {
		return betStatus;
	}

	public void setBetStatus(boolean betStatus) {
		this.betStatus = betStatus;
	}
}