package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InfoGameController implements Initializable {
	@FXML Button btnIGx;
	@FXML Button btnIGexit;
	
	@FXML Label labelIGgameno;
	@FXML Label labelIGgamedate;
	@FXML Label labelIGgame;
	@FXML Label labelIGleague;
	@FXML Label labelIGhome;
	@FXML Label labelIGaway;
	@FXML Label labelIGincomeRate;
	@FXML Label labelIGwinRate;
	@FXML Label labelIGwinMoney;
	@FXML Label labelIGdrawRate;
	@FXML Label labelIGdrawMoney;
	@FXML Label labelIGloseRate;
	@FXML Label labelIGloseMoney;
	@FXML Label labelIGbetStatus;
	@FXML Label labelIGresult;
	@FXML Label labelIGreward;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Game game = ViewController.getSelectedGame();
		
		// 라벨 설정
		labelIGgameno.setText(String.valueOf(game.getGameNo()));
		labelIGgamedate.setText(game.getDate());
		labelIGgame.setText(game.getGame());
		labelIGleague.setText(game.getLeague());
		labelIGhome.setText(game.getHomeTeam());
		labelIGaway.setText(game.getAwayTeam());
		labelIGincomeRate.setText(String.valueOf(game.getIncomeRate()));
		labelIGwinRate.setText(String.valueOf(game.getWinRate()));
		labelIGwinMoney.setText(String.valueOf(game.getWinMoney()));
		labelIGdrawRate.setText(String.valueOf(game.getDrawRate()));
		labelIGdrawMoney.setText(String.valueOf(game.getDrawMoney()));
		labelIGloseRate.setText(String.valueOf(game.getLoseRate()));
		labelIGloseMoney.setText(String.valueOf(game.getLoseMoney()));
		labelIGbetStatus.setText(game.getBetStatus());
		labelIGresult.setText(game.getResult());
		double total = Integer.parseInt(String.valueOf(game.getWinMoney()))+
				Integer.parseInt(String.valueOf(game.getDrawMoney()))+
				Integer.parseInt(String.valueOf(game.getLoseMoney()));				
		
		double reward = total-total*game.getIncomeRate();
		
		labelIGreward.setText(String.valueOf((int)reward));
		
		// X 버튼 이벤트
		btnIGx.setOnAction((event)-> {
			Stage stage = (Stage) btnIGx.getScene().getWindow();
			stage.close();});
		// 닫기버튼 이벤트
		btnIGexit.setOnAction((event)->{
			Stage stage = (Stage) btnIGexit.getScene().getWindow();
			stage.close();});
	}
}