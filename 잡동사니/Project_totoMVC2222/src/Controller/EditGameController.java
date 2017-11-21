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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class EditGameController implements Initializable {
	@FXML private DatePicker dpEGgamedate;
	
	@FXML private ComboBox comboEGleague;
	@FXML private ComboBox comboEGgame;
	@FXML private ComboBox comboEGstatus;
	@FXML private ComboBox comboEGresult;
	
	@FXML private TextField txtEGhomeName;
	@FXML private TextField txtEGawayName;
	@FXML private TextField txtEGincomeRate;
	@FXML private TextField txtEGwinMoney;
	@FXML private TextField txtEGwinRate;
	@FXML private TextField txtEGdrawMoney;
	@FXML private TextField txtEGdrawRate;
	@FXML private TextField txtEGloseMoney;
	@FXML private TextField txtEGloseRate;
	@FXML private TextField txtEGreward;
	
	@FXML private Button btnEGcal;
	@FXML private Button btnEGedit;
	@FXML private Button btnEGcancle;
	
	private Game game = ViewController.getSelectedGame();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//수정창 설정 초기화
		editGameInit();    
		//수정버튼 이벤트
		btnEGedit.setOnAction((event)->handleBtnEdit());
		//계산버튼 이벤트
		btnEGcal.setOnAction((event)->handleBtnCal());
		//취소버튼 이벤트
		btnEGcancle.setOnAction((event)-> {
				Stage stage = (Stage) btnEGcancle.getScene().getWindow();stage.close();});
	}

	public void handleBtnCal() {
		double winMoney = Double.parseDouble(txtEGwinMoney.getText().toString());
		double drawMoney = Double.parseDouble(txtEGdrawMoney.getText().toString());
		double loseMoney = Double.parseDouble(txtEGloseMoney.getText().toString());
		double total = winMoney+drawMoney+loseMoney;
		
		double incomeRate = Double.parseDouble(txtEGincomeRate.getText().toString())/100;
		double reward = total-total*incomeRate;
		double winRate = reward/winMoney*100;
		double drawRate = reward/drawMoney*100;
		double loseRate = reward/loseMoney*100;
		
		txtEGreward.setText(String.valueOf((int)reward));
		txtEGwinRate.setText(String.valueOf((int)winRate));
		txtEGdrawRate.setText(String.valueOf((int)drawRate));
		txtEGloseRate.setText(String.valueOf((int)loseRate));		
		
		btnEGedit.setDisable(false);
	}

	public void handleBtnEdit() {
		Stage stage = (Stage) btnEGedit.getScene().getWindow();
		Game gameEdit = null;	
		GameDB gameDB = null;
		if(txtEGhomeName.getText().isEmpty()||txtEGawayName.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("경고");
			alert.setHeaderText("잘못된 입력");
			alert.setContentText("경기정보를 제대로 입력하세요.");
			alert.showAndWait();
		}else {
				gameEdit = new Game(dpEGgamedate.getValue().toString(),
        		 comboEGgame.getValue().toString(),
        		 comboEGleague.getValue().toString(),
        		 txtEGhomeName.getText(), txtEGawayName.getText(),
        		 Double.parseDouble(txtEGincomeRate.getText())/100,
        		 Double.parseDouble(txtEGwinRate.getText())/100,
        		 Integer.parseInt(txtEGwinMoney.getText()),
        		 Double.parseDouble(txtEGdrawRate.getText())/100,
        		 Integer.parseInt(txtEGdrawMoney.getText()),
        		 Double.parseDouble(txtEGloseRate.getText())/100,
        		 Integer.parseInt(txtEGloseMoney.getText()),
        		 comboEGresult.getValue().toString(),
        		 comboEGstatus.getValue().toString());
         gameEdit.setGameNo(game.getGameNo());
         stage.close();
         gameDB = new GameDB();
         gameDB.gameEdit(gameEdit, game.getGameNo());
         System.out.println(game);
         System.out.println(gameEdit);
         ViewController.data.removeAll(ViewController.data);
         ViewController.totalList();
		}	         
	}

	public void editGameInit() {
		btnEGedit.setDisable(true);
		dpEGgamedate.setEditable(false);
		txtEGwinRate.setEditable(false);
		txtEGdrawRate.setEditable(false);
		txtEGloseRate.setEditable(false);
		txtEGreward.setEditable(false);
		
		LocalDate localDate = LocalDate.parse(game.getDate());
        int incomeRate = (int) (game.getIncomeRate()*100);
        int winRate = (int)(game.getWinRate()*100);
        int drawRate = (int)(game.getDrawRate()*100);
        int loseRate = (int)(game.getLoseRate()*100);
        
        comboEGgame.setValue(game.getGame());         
        dpEGgamedate.setValue(localDate);
        comboEGleague.setValue(game.getLeague());
        comboEGstatus.setValue(game.getBetStatus());

        txtEGhomeName.setText(game.getHomeTeam());
        txtEGawayName.setText(game.getAwayTeam());
        comboEGresult.setValue(game.getResult());
        
        txtEGincomeRate.setText(String.valueOf(incomeRate));
        txtEGwinMoney.setText(String.valueOf(game.getWinMoney()));
        txtEGwinRate.setText(String.valueOf(winRate));
        txtEGdrawMoney.setText(String.valueOf(game.getDrawMoney()));
        txtEGdrawRate.setText(String.valueOf(drawRate));
        txtEGloseMoney.setText(String.valueOf(game.getLoseMoney()));
        txtEGloseRate.setText(String.valueOf(loseRate));
        
        String check = comboEGgame.getValue().toString();
		if(check.equals("축구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("분데스리가");
			leagueList.add("월드컵");
			leagueList.add("프리메라리가");
			leagueList.add("프리미어리그");
			comboEGleague.setItems(leagueList);
		}else if(check.equals("야구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("KBO");
			leagueList.add("WBC");
			leagueList.add("MLB");
			comboEGleague.setItems(leagueList);
		}else if(check.equals("농구")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("국가대항전");
			leagueList.add("KBL");
			leagueList.add("NBA");
			comboEGleague.setItems(leagueList);
		}
		
		ObservableList<String> gameList = FXCollections.observableArrayList();
		gameList.add("축구");
		gameList.add("농구");
		gameList.add("야구");
		comboEGgame.setItems(gameList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add("진행");
		statusList.add("중지");
		comboEGstatus.setItems(statusList);
		
		ObservableList<String> statusResult = FXCollections.observableArrayList();
		statusResult.add("경기 전");
		statusResult.add("승");
		statusResult.add("무");
		statusResult.add("패");
		comboEGresult.setItems(statusResult);
		
		DecimalFormat format = new DecimalFormat("##");
		DecimalFormat moneyFormat = new DecimalFormat("###########");
		
		txtEGincomeRate.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGwinRate.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGwinMoney.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGdrawRate.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGdrawMoney.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGloseRate.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGloseMoney.setTextFormatter(new TextFormatter<>(event -> {
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
		txtEGwinMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnEGedit.setDisable(true);
				}
			}
		});
		txtEGdrawMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnEGedit.setDisable(true);
				}
			}
		});
		txtEGloseMoney.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnEGedit.setDisable(true);
				}
			}
		});
		txtEGincomeRate.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.equals(newValue)==false) {
					btnEGedit.setDisable(true);
				}
			}
		});
	}
}
