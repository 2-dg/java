package Controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Model.Game;
import application.GameDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class RegGameController implements Initializable{
	@FXML private DatePicker dpRGgamedate;

	@FXML private ComboBox comboRGgame;
	@FXML private ComboBox comboRGleague;
	@FXML private ComboBox comboRGstatus;
	@FXML private ComboBox comboRGresult;
	
	@FXML private TextField txtRGhomeName;
	@FXML private TextField txtRGawayName;
	@FXML private TextField txtRGincomeRate;
	@FXML private TextField txtRGwinMoney;
	@FXML private TextField txtRGwinRate;
	@FXML private TextField txtRGdrawMoney;
	@FXML private TextField txtRGdrawRate;
	@FXML private TextField txtRGloseMoney;
	@FXML private TextField txtRGloseRate;
	@FXML private TextField txtRGreward;
	
	@FXML private Button btnRGcal;
	@FXML private Button btnRGcancle;
	@FXML private Button btnRGregist;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//등록창 팝업시 기본 설정자
		initRG();
		//게임 콤보박스 선택시 리그 콤보박스 변경하는 메소드
		comboRGgame.setOnAction((Event event)->	handleComboGame());
		//취소버튼 이벤트
		btnRGcancle.setOnAction((event)->handleBtnCancle());
		//등록버튼 이벤트
		btnRGregist.setOnAction((event)->handleBtnRegist());
		//계산버튼 이벤트
		btnRGcal.setOnAction((event)->handleBtnCal());
	}//end of initialize
	
	public void handleBtnCal() {
		double winMoney = Double.parseDouble(txtRGwinMoney.getText().toString());
		double drawMoney = Double.parseDouble(txtRGdrawMoney.getText().toString());
		double loseMoney = Double.parseDouble(txtRGloseMoney.getText().toString());
		double total = winMoney+drawMoney+loseMoney;
		
		double incomeRate = Double.parseDouble(txtRGincomeRate.getText().toString())/100;
		double reward = total-total*incomeRate;
		double winRate = reward/winMoney*100;
		double drawRate = reward/drawMoney*100;
		double loseRate = reward/loseMoney*100;
		
		txtRGreward.setText(String.valueOf((int)reward));
		txtRGwinRate.setText(String.valueOf((int)winRate));
		txtRGdrawRate.setText(String.valueOf((int)drawRate));
		txtRGloseRate.setText(String.valueOf((int)loseRate));
		
		btnRGregist.setDisable(false);
	}

	public void initRG() {
		dpRGgamedate.setValue(LocalDate.now());
		dpRGgamedate.setEditable(false);
		comboRGleague.setDisable(true);
		txtRGwinRate.setEditable(false);
		txtRGdrawRate.setEditable(false);
		txtRGloseRate.setEditable(false);
		txtRGreward.setEditable(false);
		btnRGregist.setDisable(true);
		
		ObservableList<String> gameList = FXCollections.observableArrayList();
		gameList.add("축구");
		gameList.add("농구");
		gameList.add("야구");
		comboRGgame.setItems(gameList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add("진행");
		statusList.add("중지");
		comboRGstatus.setItems(statusList);
		
		ObservableList<String> statusResult = FXCollections.observableArrayList();
		statusResult.add("경기 전");
		statusResult.add("승");
		statusResult.add("무");
		statusResult.add("패");
		comboRGresult.setItems(statusResult);
		
		if(txtRGincomeRate.getText().isEmpty()) {
			txtRGincomeRate.setText("10");
		}
		if(txtRGwinRate.getText().isEmpty()) {
			txtRGwinRate.setText("270");
		}
		if(txtRGwinMoney.getText().isEmpty()) {
			txtRGwinMoney.setText("10000");
		}
		if(txtRGdrawRate.getText().isEmpty()) {
			txtRGdrawRate.setText("270");
		}
		if(txtRGdrawMoney.getText().isEmpty()) {
			txtRGdrawMoney.setText("10000");
		}
		if(txtRGloseRate.getText().isEmpty()) {
			txtRGloseRate.setText("270");
		}
		if(txtRGloseMoney.getText().isEmpty()) {
			txtRGloseMoney.setText("10000");
		}		
		
		DecimalFormat format = new DecimalFormat("##");
		DecimalFormat moneyFormat = new DecimalFormat("###########");
		
		txtRGincomeRate.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==3) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGwinRate.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==4) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGwinMoney.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = moneyFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==12) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGdrawRate.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
					||event.getControlNewText().length()==4) {
				return null;
			}else {
				return event;
			}}));
		txtRGdrawMoney.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = moneyFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==12) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGloseRate.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==4) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGloseMoney.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = moneyFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==12) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtRGwinMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnRGregist.setDisable(true);
				}
			}
		});
		txtRGdrawMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnRGregist.setDisable(true);
				}
			}
		});
		txtRGloseMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnRGregist.setDisable(true);
				}
			}
		});
		txtRGincomeRate.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnRGregist.setDisable(true);
				}
			}
		});
	}

	public void handleComboGame() {
		comboRGleague.setDisable(false);
		String check = (String) comboRGgame.getSelectionModel().getSelectedItem();
		if(check.equals("축구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("분데스리가");
			leagueList.add("월드컵");
			leagueList.add("프리메라리가");
			leagueList.add("프리미어리그");
			comboRGleague.setItems(leagueList);
		}else if(check.equals("야구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("KBO");
			leagueList.add("WBC");
			leagueList.add("MLB");
			comboRGleague.setItems(leagueList);
		}else if(check.equals("농구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("KBL");
			leagueList.add("NBA");
			comboRGleague.setItems(leagueList);
		}
	}
	
	public void handleBtnRegist() {
		Game game = null;
		GameDB gameDB = new GameDB();
		double incomeRate = Double.parseDouble(txtRGincomeRate.getText().trim())/100;
		double winRate = Double.parseDouble(txtRGwinRate.getText().trim())/100;
		double drawRate = Double.parseDouble(txtRGdrawRate.getText().trim())/100;
		double loseRate = Double.parseDouble(txtRGloseRate.getText().trim())/100;
		
		try {
		game = new Game(dpRGgamedate.getValue().toString(),
				comboRGgame.getSelectionModel().getSelectedItem().toString(),
				comboRGleague.getSelectionModel().getSelectedItem().toString().trim(),
				txtRGhomeName.getText().trim(), txtRGawayName.getText().trim(),
				incomeRate,	winRate, Integer.parseInt(txtRGwinMoney.getText().trim()),
				drawRate, Integer.parseInt(txtRGdrawMoney.getText().trim()),
				loseRate,Integer.parseInt(txtRGdrawMoney.getText().trim()),
				comboRGresult.getSelectionModel().getSelectedItem().toString().trim(),
				comboRGstatus.getSelectionModel().getSelectedItem().toString().trim());
				gameDB.gameRegist(game);
		}catch(java.lang.NullPointerException e) {
			gameDB = null;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("경고");
			alert.setContentText("제대로 된 경기정보를 입력하세요.");
			alert.setHeaderText("경기정보가 제대로 입력되지 않았습니다.");
			alert.showAndWait();
			btnRGregist.setDisable(true);
		}
		
		if(gameDB != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("등록");
			alert.setHeaderText("등록 완료.");
			alert.setContentText("경기 등록을 완료하였습니다.");
			alert.showAndWait();
			ViewController.data.clear();
			ViewController.totalList();
			Stage stage = (Stage) btnRGcancle.getScene().getWindow();
			stage.close();
		}
	}
	
	public void handleBtnCancle() {
		Stage stage = (Stage) btnRGcancle.getScene().getWindow();
		stage.close();
	}
}