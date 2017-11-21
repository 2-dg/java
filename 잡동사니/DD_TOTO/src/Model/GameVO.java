package Model;

import java.io.Serializable;

public class GameVO{
	private int gameNo;			//게임관리번호
	private String date;		//경기날짜
	private String game;		//경기종류
	private String league;		//리그종류

	private String homeTeam;	//홈팀이름
	private String awayTeam;	//어웨이팀 이름
	
	private double incomeRate;	//관리자 수익률
	private double winRate;		//홈팀 승리시 배당률
	private int winMoney;		//승리에 결린 금액
	private double drawRate;	//무승부시 배당률
	private int drawMoney;		//무승부에 걸린 금액
	private double loseRate;	//홈팀 패배시 배당률
	private int loseMoney;		//패배에 걸린 금액
		
	private String result;		//경기결과
	private String betStatus;	//배팅 상태 설정

	public GameVO() {
		super();
	}
	
	public GameVO(String date, String game, String league, String homeTeam, String awayTeam, double incomeRate,
			double winRate, int winMoney, double drawRate, int drawMoney, double loseRate, int loseMoney, String result,
			String betStatus) {
		super();
		this.date = date;
		this.game = game;
		this.league = league;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.incomeRate = incomeRate;
		this.winRate = winRate;
		this.winMoney = winMoney;
		this.drawRate = drawRate;
		this.drawMoney = drawMoney;
		this.loseRate = loseRate;
		this.loseMoney = loseMoney;
		this.result = result;
		this.betStatus = betStatus;
	}

	public double getReward() {
		return (winMoney+drawMoney+loseMoney)-(winMoney+drawMoney+loseMoney)*incomeRate;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public int getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(int winMoney) {
		this.winMoney = winMoney;
	}

	public int getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(int drawMoney) {
		this.drawMoney = drawMoney;
	}

	public int getLoseMoney() {
		return loseMoney;
	}

	public void setLoseMoney(int loseMoney) {
		this.loseMoney = loseMoney;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
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

	public String getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}

	public int getGameNo() {
		return gameNo;
	}

	public void setGameNo(int gameNo) {
		this.gameNo = gameNo;
	}

	@Override
	public String toString() {
		return "Game [gameNo=" + gameNo + ", date=" + date + ", game=" + game + ", league=" + league + ", homeTeam="
				+ homeTeam + ", awayTeam=" + awayTeam + ", incomeRate=" + incomeRate + ", winRate=" + winRate
				+ ", winMoney=" + winMoney + ", drawRate=" + drawRate + ", drawMoney=" + drawMoney + ", loseRate="
				+ loseRate + ", loseMoney=" + loseMoney + ", result=" + result + ", betStatus=" + betStatus + "]";
	}
}