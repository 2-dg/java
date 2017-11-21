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
	//테이블뷰 설정을 위한 객체 리스트
	public static ObservableList<Game> data = FXCollections.observableArrayList();
	//테이블뷰에서 마우스로 선택된 게임을 객체화하여 외부 컨트롤러에서도 다룰 수 있게 정적으로 선언
	private static Game selectedGame;
	
	// =================경기관리탭=================
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

	// =================경기관리탭 끝================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ==============경기관리탭 설정=======================

		// 게임관리화면 초기화
		initGM();
		
		// 테이블뷰를 클릭, 더블클릭했을 때 
		tableViewGM.setOnMouseClicked((event)-> handleTableView(event));
		// 경기등록버튼
		btnGMRegist.setOnAction((event) -> hanleBtnRegist(event));
		// 종료버튼
		btnGMExit.setOnAction((event) -> Platform.exit());
		// 상세정보 버튼
		btnGMDetail.setOnAction((event) -> handleBtnDetail(event));
		// 경기수정 버튼
		btnGMEdit.setOnAction((event)->handleBtnEdit(event));
		// 경기삭제 버튼
		btnGMDelete.setOnAction((event)->handleBtnDelete(event));
		// ==============경기관리탭 설정 끝=======================

		
		
	}//END OF INITIALIZE
	
	//테이블 뷰 클릭 이벤트
	public void handleTableView(MouseEvent event) {
		if(event.getClickCount()==1) {		
		selectedGame = tableViewGM.getSelectionModel().getSelectedItem();
		}else if(event.getClickCount()==2) {
			handleBtnDetail(null);
		}
	}

	//삭제버튼 이벤트
	public void handleBtnDelete(ActionEvent event) {
	      GameDB gameDB= null;
	      Game game = tableViewGM.getSelectionModel().getSelectedItem();
	      gameDB = new GameDB();
	      gameDB.gameDelete(game.getGameNo());
	      data.removeAll(data);
	      totalList();
	}
	
	//컨트롤러 분리를 위한 객체 전달 메소드
	public static Game getSelectedGame() {
		return selectedGame;
	}
	
	//수정버튼 이벤트
	public void handleBtnEdit(ActionEvent event) {
		 try {
			 Parent root = FXMLLoader.load(getClass().getResource("/View/EditGame.fxml"));
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnGMEdit.getScene().getWindow());
	         stage.setTitle("경기 수정");
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}
	
	//등록버튼 이벤트
	public void hanleBtnRegist(ActionEvent event) {
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnGMRegist.getScene().getWindow());
			Parent root = FXMLLoader.load(getClass().getResource("/View/RegGame.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("경기 등록");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//목록 불러오기
	public static void totalList() {
		GameDB gameDB = new GameDB();
		Game game = new Game();

		ArrayList<String> title;
		ArrayList<Game> list;

		title = gameDB.getColumnName();
		for (String str : title) {
			System.out.println("불러옴= " + str);
		}
		list = gameDB.getGameTotal();
		for (int index = 0; index < list.size(); index++) {
			game = list.get(index);
			data.add(game);
		}
	}
	
	//상세버튼 이벤트
	public void handleBtnDetail(ActionEvent event) {
	      try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnGMRegist.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/InfoGame.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("경기 상세정보");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }	
	
	//게임관리화면 초기화
	public void initGM() {
		//데이트픽커 설정
		dpGMbefore.setValue(LocalDate.now().minusDays(1));
		dpGMafter.setValue(LocalDate.now());		
		dpGMbefore.setEditable(false);
		dpGMafter.setEditable(false);
		
		totalList();
		tableViewGM.setItems(data);
		
		TableColumn tcDate = new TableColumn("경기 날짜");
		tcDate.setPrefWidth(80);
		tcDate.setStyle("-fx-alignment: CENTER");
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn tcGame = new TableColumn("경기 종목");
		tcGame.setPrefWidth(60);
		tcGame.setStyle("-fx-alignment: CENTER");
		tcGame.setCellValueFactory(new PropertyValueFactory<>("game"));
		
		TableColumn tcLeague = new TableColumn("리그명");
		tcLeague.setPrefWidth(90);
		tcLeague.setCellValueFactory(new PropertyValueFactory<>("league"));
		
		TableColumn tcHome = new TableColumn("홈 팀");
		tcHome.setPrefWidth(140);
		tcHome.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
		
		TableColumn tcAway = new TableColumn("어웨이 팀");
		tcAway.setPrefWidth(140);
		tcAway.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
		
		TableColumn tcICrate = new TableColumn("수익률");
		tcICrate.setPrefWidth(70);
		tcICrate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcICrate.setCellValueFactory(new PropertyValueFactory<>("incomeRate"));
		
		TableColumn tcWinRate = new TableColumn("승리");
		tcWinRate.setPrefWidth(70);
		tcWinRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcWinRate.setCellValueFactory(new PropertyValueFactory<>("winRate"));
		
		TableColumn tcDrawRate = new TableColumn("무승부");
		tcDrawRate.setPrefWidth(70);
		tcDrawRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcDrawRate.setCellValueFactory(new PropertyValueFactory<>("drawRate"));
		
		TableColumn tcLoseRate = new TableColumn("패배");
		tcLoseRate.setPrefWidth(70);
		tcLoseRate.setStyle("-fx-alignment: CENTER-RIGHT");
		tcLoseRate.setCellValueFactory(new PropertyValueFactory<>("loseRate"));
		
		TableColumn tcBetStatus = new TableColumn("배팅 상태");
		tcBetStatus.setPrefWidth(70);
		tcBetStatus.setStyle("-fx-alignment: CENTER");
		tcBetStatus.setCellValueFactory(new PropertyValueFactory<>("betStatus"));
		
		TableColumn tcResult = new TableColumn("경기 현황");
		tcResult.setPrefWidth(70);
		tcResult.setStyle("-fx-alignment: CENTER");
		tcResult.setCellValueFactory(new PropertyValueFactory<>("result"));
		
		tableViewGM.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcICrate, tcWinRate, tcDrawRate,
				tcLoseRate, tcBetStatus, tcResult);
	}
}