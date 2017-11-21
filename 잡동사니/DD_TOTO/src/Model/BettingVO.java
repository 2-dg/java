package Model;

public class BettingVO {
	private int betNo;
	private String id;
	private String gameDate;
	private String game;
	private String league;
	private String homeTeam;
	private String awayTeam;
	
	private double winRate;
	private int winMoney;
	private double drawRate;
	private int drawMoney;
	private double loseRate;
	private int loseMoney;
	
	private String betPart;
	private int bettedMoney;
	private String gameResult;
	private String hit;
	private String pay;
	public int getBetNo() {
		return betNo;
	}
	public void setBetNo(int betNo) {
		this.betNo = betNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGameDate() {
		return gameDate;
	}
	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
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
	public double getWinRate() {
		return winRate;
	}
	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}
	public int getWinMoney() {
		return winMoney;
	}
	public void setWinMoney(int winMoney) {
		this.winMoney = winMoney;
	}
	public double getDrawRate() {
		return drawRate;
	}
	public void setDrawRate(double drawRate) {
		this.drawRate = drawRate;
	}
	public int getDrawMoney() {
		return drawMoney;
	}
	public void setDrawMoney(int drawMoney) {
		this.drawMoney = drawMoney;
	}
	public double getLoseRate() {
		return loseRate;
	}
	public void setLoseRate(double loseRate) {
		this.loseRate = loseRate;
	}
	public int getLoseMoney() {
		return loseMoney;
	}
	public void setLoseMoney(int loseMoney) {
		this.loseMoney = loseMoney;
	}
	public String getBetPart() {
		return betPart;
	}
	public void setBetPart(String betPart) {
		this.betPart = betPart;
	}
	public int getBettedMoney() {
		return bettedMoney;
	}
	public void setBettedMoney(int bettedMoney) {
		this.bettedMoney = bettedMoney;
	}
	public String getGameResult() {
		return gameResult;
	}
	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	@Override
	public String toString() {
		return "BettingVO [betNo=" + betNo + ", id=" + id + ", gameDate=" + gameDate + ", game=" + game + ", league="
				+ league + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", winRate=" + winRate + ", winMoney="
				+ winMoney + ", drawRate=" + drawRate + ", drawMoney=" + drawMoney + ", loseRate=" + loseRate
				+ ", loseMoney=" + loseMoney + ", betPart=" + betPart + ", bettedMoney=" + bettedMoney + ", gameResult="
				+ gameResult + ", hit=" + hit + ", pay=" + pay + "]";
	}
	
}
