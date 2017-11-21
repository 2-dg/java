package Model;

import java.io.Serializable;

public class GameVO{
	private int gameNo;			//���Ӱ�����ȣ
	private String date;		//��⳯¥
	private String game;		//�������
	private String league;		//��������

	private String homeTeam;	//Ȩ���̸�
	private String awayTeam;	//������� �̸�
	
	private double incomeRate;	//������ ���ͷ�
	private double winRate;		//Ȩ�� �¸��� ����
	private int winMoney;		//�¸��� �Ḱ �ݾ�
	private double drawRate;	//���ºν� ����
	private int drawMoney;		//���ºο� �ɸ� �ݾ�
	private double loseRate;	//Ȩ�� �й�� ����
	private int loseMoney;		//�й迡 �ɸ� �ݾ�
		
	private String result;		//�����
	private String betStatus;	//���� ���� ����

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