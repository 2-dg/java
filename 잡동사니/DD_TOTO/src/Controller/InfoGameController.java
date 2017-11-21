package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.BettingVO;
import Model.GameVO;
import dao.BettingDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InfoGameController implements Initializable {
	@FXML Button btnIGx;
	@FXML Button btnIGexit;
	
	@FXML TableView tableInfo;
	
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
	
	ObservableList<BettingVO> listOfBet = FXCollections.observableArrayList();
	GameVO gameVO = ViewController.getSelectedGame();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//정보창 초기화
		initInfo();

		// X 버튼 이벤트
		btnIGx.setOnAction((event)-> {
			Stage stage = (Stage) btnIGx.getScene().getWindow();
			stage.close();});
		// 닫기버튼 이벤트
		btnIGexit.setOnAction((event)->{
			Stage stage = (Stage) btnIGexit.getScene().getWindow();
			stage.close();});
	}

	private void initInfo() {
		// 라벨 설정
		labelIGgameno.setText(String.valueOf(gameVO.getGameNo()));
		labelIGgamedate.setText(gameVO.getDate());
		labelIGgame.setText(gameVO.getGame());
		labelIGleague.setText(gameVO.getLeague());
		labelIGhome.setText(gameVO.getHomeTeam());
		labelIGaway.setText(gameVO.getAwayTeam());
		labelIGincomeRate.setText(String.valueOf(gameVO.getIncomeRate()));
		labelIGwinRate.setText(String.valueOf(gameVO.getWinRate()));
		labelIGwinMoney.setText(String.valueOf(gameVO.getWinMoney()));
		labelIGdrawRate.setText(String.valueOf(gameVO.getDrawRate()));
		labelIGdrawMoney.setText(String.valueOf(gameVO.getDrawMoney()));
		labelIGloseRate.setText(String.valueOf(gameVO.getLoseRate()));
		labelIGloseMoney.setText(String.valueOf(gameVO.getLoseMoney()));
		labelIGbetStatus.setText(gameVO.getBetStatus());
		labelIGresult.setText(gameVO.getResult());
		double total = Integer.parseInt(String.valueOf(gameVO.getWinMoney()))+
				Integer.parseInt(String.valueOf(gameVO.getDrawMoney()))+
				Integer.parseInt(String.valueOf(gameVO.getLoseMoney()));				
		double reward = total-total*gameVO.getIncomeRate();
		labelIGreward.setText(String.valueOf((int)reward));
		
		TableColumn tcID = new TableColumn("ID");
		tcID.setPrefWidth(71);
		tcID.setStyle("-fx-alignment: CENTER");
		tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn tcBetPart = new TableColumn("베팅");
		tcBetPart.setPrefWidth(40);
		tcBetPart.setStyle("-fx-alignment: CENTER");
		tcBetPart.setCellValueFactory(new PropertyValueFactory<>("betPart"));
		
		TableColumn tcBettedMoney = new TableColumn("베팅금액");
		tcBettedMoney.setPrefWidth(90);
		tcBettedMoney.setStyle("-fx-alignment: CENTER-RIGHT");
		tcBettedMoney.setCellValueFactory(new PropertyValueFactory<>("bettedMoney"));
		
		TableColumn tcHit = new TableColumn("적중여부");
		tcHit.setPrefWidth(70);
		tcHit.setStyle("-fx-alignment: CENTER");
		tcHit.setCellValueFactory(new PropertyValueFactory<>("hit"));
		
		TableColumn tcPay = new TableColumn("지급여부");
		tcPay.setPrefWidth(70);
		tcPay.setStyle("-fx-alignment: CENTER");
		tcPay.setCellValueFactory(new PropertyValueFactory<>("pay"));
		
		tableInfo.getColumns().addAll(tcID,tcBetPart,tcBettedMoney, tcHit, tcPay);
		
		listOfBet.removeAll(listOfBet);
		totalListClientBet();
		tableInfo.setItems(listOfBet);
		
	}
	
	public void totalListClientBet() {
		BettingDAO betDAO = new BettingDAO();
		ArrayList<BettingVO> list = betDAO.getBettinguser(gameVO);
		for(BettingVO bet : list) {
			listOfBet.add(bet);
		}
	}
}