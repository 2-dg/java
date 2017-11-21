package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Game;
import application.GameDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewController implements Initializable {
	//���̺�� ������ ���� ��ü ����Ʈ
	public static ObservableList<Game> data = FXCollections.observableArrayList();
	//���̺�信�� ���콺�� ���õ� ������ ��üȭ�Ͽ� �ܺ� ��Ʈ�ѷ������� �ٷ� �� �ְ� �������� ����
	private static Game selectedGame;
	
	// =================��������=================
	@FXML private TableView<Game> tableViewGM;

	@FXML private DatePicker dpGMbefore;
	@FXML private DatePicker dpGMafter;

	@FXML private ComboBox comboGMgame;
	@FXML private ComboBox comboGMleague;

	@FXML private TextField txtGMhome;
	@FXML private TextField txtGMleague;

	@FXML private Button btnGMDetail;
	@FXML private Button btnGMExit;
	@FXML private Button btnGMsearch;
	@FXML private Button btnGMRegist;
	@FXML private Button btnGMEdit;
	@FXML private Button btnGMDelete;

	// =================�������� ��================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ==============�������� ����=======================

		// ���Ӱ���ȭ�� �ʱ�ȭ
		initGM();
		
		// ���̺�並 Ŭ��, ����Ŭ������ �� 
		tableViewGM.setOnMouseClicked((event)-> handleTableView(event));
		// ����Ϲ�ư
		btnGMRegist.setOnAction((event) -> hanleBtnRegist(event));
		// �����ư
		btnGMExit.setOnAction((event) -> Platform.exit());
		// ������ ��ư
		btnGMDetail.setOnAction((event) -> handleBtnDetail(event));
		// ������ ��ư
		btnGMEdit.setOnAction((event)->handleBtnEdit(event));
		// ������ ��ư
		btnGMDelete.setOnAction((event)->handleBtnDelete(event));
		// ==============�������� ���� ��=======================

		
		
	}//END OF INITIALIZE
	
	//���̺� �� Ŭ�� �̺�Ʈ
	public void handleTableView(MouseEvent event) {
		if(event.getClickCount()==1) {		
		selectedGame = tableViewGM.getSelectionModel().getSelectedItem();
		}else if(event.getClickCount()==2) {
			handleBtnDetail(null);
		}
	}

	//������ư �̺�Ʈ
	public void handleBtnDelete(ActionEvent event) {
	      GameDB gameDB= null;
	      Game game = tableViewGM.getSelectionModel().getSelectedItem();
	      gameDB = new GameDB();
	      gameDB.gameDelete(game.getGameNo());
	      data.removeAll(data);
	      totalList();
	}
	
	//��Ʈ�ѷ� �и��� ���� ��ü ���� �޼ҵ�
	public static Game getSelectedGame() {
		return selectedGame;
	}
	
	//������ư �̺�Ʈ
	public void handleBtnEdit(ActionEvent event) {
		 try {
			 Parent root = FXMLLoader.load(getClass().getResource("/View/EditGame.fxml"));
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnGMEdit.getScene().getWindow());
	         stage.setTitle("��� ����");
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}
	
	//��Ϲ�ư �̺�Ʈ
	public void hanleBtnRegist(ActionEvent event) {
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnGMRegist.getScene().getWindow());
			Parent root = FXMLLoader.load(getClass().getResource("/View/RegGame.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("��� ���");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��� �ҷ�����
	public static void totalList() {
		GameDB gameDB = new GameDB();
		Game game = new Game();

		ArrayList<String> title;
		ArrayList<Game> list;

		title = gameDB.getColumnName();
		for (String str : title) {
			System.out.println("�ҷ���= " + str);
		}
		list = gameDB.getGameTotal();
		for (int index = 0; index < list.size(); index++) {
			game = list.get(index);
			data.add(game);
		}
	}
	
	//�󼼹�ư �̺�Ʈ
	public void handleBtnDetail(ActionEvent event) {
	      try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnGMRegist.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/InfoGame.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("��� ������");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }	
	
	//���Ӱ���ȭ�� �ʱ�ȭ
	public void initGM() {
		//����Ʈ��Ŀ ����
		dpGMbefore.setValue(LocalDate.now().minusDays(1));
		dpGMafter.setValue(LocalDate.now());		
		dpGMbefore.setEditable(false);
		dpGMafter.setEditable(false);
		
		totalList();
		tableViewGM.setItems(data);
		
		TableColumn tcDate = new TableColumn("��� ��¥");
		tcDate.setPrefWidth(80);
		tcDate.setStyle("-fx-alignment: CENTER");
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn tcGame = new TableColumn("��� ����");
		tcGame.setPrefWidth(60);
		tcGame.setStyle("-fx-alignment: CENTER");
		tcGame.setCellValueFactory(new PropertyValueFactory<>("game"));
		
		TableColumn tcLeague = new TableColumn("���׸�");
		tcLeague.setPrefWidth(90);
		tcLeague.setCellValueFactory(new PropertyValueFactory<>("league"));
		
		TableColumn tcHome = new TableColumn("Ȩ ��");
		tcHome.setPrefWidth(140);
		tcHome.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
		
		TableColumn tcAway = new TableColumn("����� ��");
		tcAway.setPrefWidth(140);
		tcAway.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
		
		TableColumn tcICrate = new TableColumn("���ͷ�");
		tcICrate.setPrefWidth(70);
		tcICrate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcICrate.setCellValueFactory(new PropertyValueFactory<>("incomeRate"));
		
		TableColumn tcWinRate = new TableColumn("�¸�");
		tcWinRate.setPrefWidth(70);
		tcWinRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcWinRate.setCellValueFactory(new PropertyValueFactory<>("winRate"));
		
		TableColumn tcDrawRate = new TableColumn("���º�");
		tcDrawRate.setPrefWidth(70);
		tcDrawRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcDrawRate.setCellValueFactory(new PropertyValueFactory<>("drawRate"));
		
		TableColumn tcLoseRate = new TableColumn("�й�");
		tcLoseRate.setPrefWidth(70);
		tcLoseRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcLoseRate.setCellValueFactory(new PropertyValueFactory<>("loseRate"));
		
		TableColumn tcBetStatus = new TableColumn("���� ����");
		tcBetStatus.setPrefWidth(70);
		tcBetStatus.setStyle("-fx-alignment: CENTER");
		tcBetStatus.setCellValueFactory(new PropertyValueFactory<>("betStatus"));
		
		TableColumn tcResult = new TableColumn("��� ��Ȳ");
		tcResult.setPrefWidth(70);
		tcResult.setStyle("-fx-alignment: CENTER");
		tcResult.setCellValueFactory(new PropertyValueFactory<>("result"));
		
		tableViewGM.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcICrate, tcWinRate, tcDrawRate,
				tcLoseRate, tcBetStatus, tcResult);
	}
}