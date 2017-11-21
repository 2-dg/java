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
		//���â �˾��� �⺻ ������
		initRG();
		//���� �޺��ڽ� ���ý� ���� �޺��ڽ� �����ϴ� �޼ҵ�
		comboRGgame.setOnAction((Event event)->	handleComboGame());
		//��ҹ�ư �̺�Ʈ
		btnRGcancle.setOnAction((event)->handleBtnCancle());
		//��Ϲ�ư �̺�Ʈ
		btnRGregist.setOnAction((event)->handleBtnRegist());
		//����ư �̺�Ʈ
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
		gameList.add("�౸");
		gameList.add("��");
		gameList.add("�߱�");
		comboRGgame.setItems(gameList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add("����");
		statusList.add("����");
		comboRGstatus.setItems(statusList);
		
		ObservableList<String> statusResult = FXCollections.observableArrayList();
		statusResult.add("��� ��");
		statusResult.add("��");
		statusResult.add("��");
		statusResult.add("��");
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
		if(check.equals("�౸")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("����������");
			leagueList.add("�е�������");
			leagueList.add("������");
			leagueList.add("�����޶󸮰�");
			leagueList.add("�����̾��");
			comboRGleague.setItems(leagueList);
		}else if(check.equals("�߱�")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("����������");
			leagueList.add("KBO");
			leagueList.add("WBC");
			leagueList.add("MLB");
			comboRGleague.setItems(leagueList);
		}else if(check.equals("��")) {
			ObservableList<String> leagueList = FXCollections.observableArrayList();
			leagueList.add("����������");
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
			alert.setTitle("���");
			alert.setContentText("����� �� ��������� �Է��ϼ���.");
			alert.setHeaderText("��������� ����� �Էµ��� �ʾҽ��ϴ�.");
			alert.showAndWait();
			btnRGregist.setDisable(true);
		}
		
		if(gameDB != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���");
			alert.setHeaderText("��� �Ϸ�.");
			alert.setContentText("��� ����� �Ϸ��Ͽ����ϴ�.");
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