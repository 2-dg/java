package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import Model.BettingVO;
import Model.GameVO;
import dao.BettingDAO;
import dao.GameDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientController implements Initializable{
	
	public static ObservableList<GameVO> listOfGamesInClient = FXCollections.observableArrayList();
	public static ObservableList<BettingVO> listOfBettings = FXCollections.observableArrayList();
	
	@FXML private TableView tableViewClient;
	@FXML private TableView tableViewClientBet;
	
	@FXML private DatePicker dpClientBefore;
	@FXML private DatePicker dpClientBet;
	
	@FXML private ComboBox comboClientGame;
	@FXML private ComboBox comboClientLeague;
	
	@FXML private TextField txtClientHome;
	@FXML private TextField txtClientLeague;
	
	@FXML private Button btnClientSearch; 
	@FXML private Button btnClientBet;
	@FXML private Button btnClientInfo;
	@FXML private Button btnClientExit;
	@FXML private Button btnClientBetSearch;
	@FXML private Button btnF51;
	@FXML private Button btnF52;
	@FXML private Button btnClientChat;
	
	private static GameVO selectedGame;
	private static Socket socket;
	private TextArea textArea;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//서버와 연결
		connectToServer();
		//클라리언트창 초기화
		initClient();
		//테이블 뷰 더블클릭 이벤트
		tableViewClient.setOnMouseClicked((event)-> handleTableView(event));
		//베팅버튼 이벤트
		btnClientBet.setOnAction((event)-> handleBtnBet(event));
		//종료버튼 이벤트
		btnClientExit.setOnAction((event)-> System.exit(0));
		//상세정보버튼 이벤트
		btnClientInfo.setOnAction((event)-> handleBtnInfo(event));
		//채팅창 이번트
		btnClientChat.setOnAction((event)-> handleBtnChat(event));
		
		
		
		
		//내 베팅창 초기화
		initBet();
		
		btnF51.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				listOfGamesInClient.removeAll(listOfGamesInClient);
				totalListClient();
				tableViewClient.setItems(listOfGamesInClient);
			}
		});
		btnF52.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				listOfBettings.removeAll(listOfBettings);
				totalListClientBet();
				tableViewClientBet.setItems(listOfBettings);
			}
		});		
	}
	
	public void handleBtnChat(ActionEvent event) {
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			Parent root = FXMLLoader.load(getClass().getResource("/View/ClientChatting.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("운영자와 채팅");
			stage.setScene(scene);
			stage.setAlwaysOnTop(true);
			stage.show();
			textArea = (TextArea) root.lookup("#txtAclient");
			TextField textField = (TextField) root.lookup("#txtFclient");
			Button btnChatExit = (Button) root.lookup("#btnChatExit");
			Button btnChatSend = (Button) root.lookup("#btnChatSend");
			textArea.setEditable(false);
			
			btnChatSend.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String sendMessage = LoginController.getPresentMember().getId()+" : "+textField.getText();
					textArea.appendText(sendMessage+"\n");
					send("c"+sendMessage);
					textField.setText("");
				}
			});
			btnChatExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					stage.close();					
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initBet() {
		
		dpClientBet.setValue(LocalDate.now());		
		dpClientBet.setEditable(false);
		
		TableColumn tcDate = new TableColumn("경기 날짜");
		tcDate.setPrefWidth(90);
		tcDate.setStyle("-fx-alignment: CENTER");
		tcDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
		
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
		
		TableColumn tcBetPart = new TableColumn("베팅");
		tcBetPart.setPrefWidth(40);
		tcBetPart.setStyle("-fx-alignment: CENTER");
		tcBetPart.setCellValueFactory(new PropertyValueFactory<>("betPart"));
		
		TableColumn tcBettedMoney = new TableColumn("베팅금액");
		tcBettedMoney.setPrefWidth(70);
		tcBettedMoney.setStyle("-fx-alignment: CENTER-RIGHT");
		tcBettedMoney.setCellValueFactory(new PropertyValueFactory<>("bettedMoney"));
		
		tableViewClientBet.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcWinRate, tcDrawRate,
				tcLoseRate,tcBetPart,tcBettedMoney);
		
		listOfBettings.removeAll(listOfBettings);
		totalListClientBet();
		tableViewClientBet.setItems(listOfBettings);
		
	}

	//상세정보버튼 이벤트
	public void handleBtnInfo(ActionEvent event) {
		try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnClientBet.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/ClientGraph.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("게임 분석");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	//베팅버튼 이벤트
	public void handleBtnBet(ActionEvent event) {
		try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnClientBet.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/BetGame.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("게임베팅");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	//테이블뷰 클릭 이벤트
	public void handleTableView(MouseEvent event) {
		if(event.getClickCount()==1) {		
			selectedGame = (GameVO) tableViewClient.getSelectionModel().getSelectedItem();
			}else if(event.getClickCount()==2) {
			try {
				handleBtnBet(null);
			} catch (Exception e) {
			}
		}
	}

	//클라이언트창 초기화
	private void initClient() {
		
		dpClientBefore.setValue(LocalDate.now());		
		dpClientBefore.setEditable(false);
		
		TableColumn tcDate = new TableColumn("경기 날짜");
		tcDate.setPrefWidth(90);
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
		
		tableViewClient.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcWinRate, tcDrawRate,
				tcLoseRate, tcBetStatus, tcResult);
		
		listOfGamesInClient.removeAll(listOfGamesInClient);
		totalListClient();
		tableViewClient.setItems(listOfGamesInClient);
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, kk:mm:ss");
		send(sdf.format(dt).toString()+", ID : "+LoginController.getPresentMember().getId()+", Entered.");
	}

	//클라이언트에서 목록 불러오기
	public static void totalListClient() {
		GameDAO gameDAO = new GameDAO();
		ArrayList<GameVO> list = gameDAO.getGameTotal();
		for(GameVO game : list) {
			listOfGamesInClient.add(game);
		}
	}
	
	public static void totalListClientBet() {
		BettingDAO betDAO = new BettingDAO();
		ArrayList<BettingVO> list = betDAO.getPersnalBettingTotal(LoginController.getPresentMember().getId());
		for(BettingVO bet : list) {
			listOfBettings.add(bet);
		}
	}
	
	//선택된 게임 반환
	public static GameVO getSelectedGame() {
		return selectedGame;
	}
	
	//연결 소켓 반환
	public static Socket getSocket() {
		return socket;
	}
	
	public void connectToServer() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 7777));
					System.out.println("연결완료");
				}catch(Exception e) {
					System.out.println("서버 오류");
					Platform.exit();
				}
				receive();
			}
		};
		thread.start();	
	}
	
	//데이터 받는 메소드
	void receive() {
		while(true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if(readByteCount == -1) { throw new IOException(); }
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
//				System.out.println("클라이언트에서 받은 데이터 : "+data);
				if(data.charAt(0)=='c') {
					data = data.substring(1, data.length());
					textArea.appendText(data+"\n");					
				}				
			} catch (Exception e) {
				stopClient();
				e.printStackTrace();
				break;
			}
		}
	}
	
	void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {		
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
				} catch(Exception e) {
					stopClient();
				}				
			}
		};
		thread.start();
	}
	
	//클라이언트 종료
	public void stopClient() {
		try {
			System.out.println("연결 끊음");
			if(socket!=null&&!socket.isClosed()) {
				socket.close();
			}
		}catch(Exception e) {e.printStackTrace();}
	}
}