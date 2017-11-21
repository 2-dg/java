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
		//������ ����
		connectToServer();
		//Ŭ�󸮾�Ʈâ �ʱ�ȭ
		initClient();
		//���̺� �� ����Ŭ�� �̺�Ʈ
		tableViewClient.setOnMouseClicked((event)-> handleTableView(event));
		//���ù�ư �̺�Ʈ
		btnClientBet.setOnAction((event)-> handleBtnBet(event));
		//�����ư �̺�Ʈ
		btnClientExit.setOnAction((event)-> System.exit(0));
		//��������ư �̺�Ʈ
		btnClientInfo.setOnAction((event)-> handleBtnInfo(event));
		//ä��â �̹�Ʈ
		btnClientChat.setOnAction((event)-> handleBtnChat(event));
		
		
		
		
		//�� ����â �ʱ�ȭ
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
			stage.setTitle("��ڿ� ä��");
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
		
		TableColumn tcDate = new TableColumn("��� ��¥");
		tcDate.setPrefWidth(90);
		tcDate.setStyle("-fx-alignment: CENTER");
		tcDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
		
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
		
		TableColumn tcBetPart = new TableColumn("����");
		tcBetPart.setPrefWidth(40);
		tcBetPart.setStyle("-fx-alignment: CENTER");
		tcBetPart.setCellValueFactory(new PropertyValueFactory<>("betPart"));
		
		TableColumn tcBettedMoney = new TableColumn("���ñݾ�");
		tcBettedMoney.setPrefWidth(70);
		tcBettedMoney.setStyle("-fx-alignment: CENTER-RIGHT");
		tcBettedMoney.setCellValueFactory(new PropertyValueFactory<>("bettedMoney"));
		
		tableViewClientBet.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcWinRate, tcDrawRate,
				tcLoseRate,tcBetPart,tcBettedMoney);
		
		listOfBettings.removeAll(listOfBettings);
		totalListClientBet();
		tableViewClientBet.setItems(listOfBettings);
		
	}

	//��������ư �̺�Ʈ
	public void handleBtnInfo(ActionEvent event) {
		try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnClientBet.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/ClientGraph.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("���� �м�");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	//���ù�ư �̺�Ʈ
	public void handleBtnBet(ActionEvent event) {
		try {
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnClientBet.getScene().getWindow());
	         Parent root = FXMLLoader.load(getClass().getResource("/View/BetGame.fxml"));
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("���Ӻ���");
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	//���̺�� Ŭ�� �̺�Ʈ
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

	//Ŭ���̾�Ʈâ �ʱ�ȭ
	private void initClient() {
		
		dpClientBefore.setValue(LocalDate.now());		
		dpClientBefore.setEditable(false);
		
		TableColumn tcDate = new TableColumn("��� ��¥");
		tcDate.setPrefWidth(90);
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
		
		tableViewClient.getColumns().addAll(tcDate, tcGame, tcLeague, tcHome, tcAway, tcWinRate, tcDrawRate,
				tcLoseRate, tcBetStatus, tcResult);
		
		listOfGamesInClient.removeAll(listOfGamesInClient);
		totalListClient();
		tableViewClient.setItems(listOfGamesInClient);
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, kk:mm:ss");
		send(sdf.format(dt).toString()+", ID : "+LoginController.getPresentMember().getId()+", Entered.");
	}

	//Ŭ���̾�Ʈ���� ��� �ҷ�����
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
	
	//���õ� ���� ��ȯ
	public static GameVO getSelectedGame() {
		return selectedGame;
	}
	
	//���� ���� ��ȯ
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
					System.out.println("����Ϸ�");
				}catch(Exception e) {
					System.out.println("���� ����");
					Platform.exit();
				}
				receive();
			}
		};
		thread.start();	
	}
	
	//������ �޴� �޼ҵ�
	void receive() {
		while(true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if(readByteCount == -1) { throw new IOException(); }
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
//				System.out.println("Ŭ���̾�Ʈ���� ���� ������ : "+data);
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
	
	//Ŭ���̾�Ʈ ����
	public void stopClient() {
		try {
			System.out.println("���� ����");
			if(socket!=null&&!socket.isClosed()) {
				socket.close();
			}
		}catch(Exception e) {e.printStackTrace();}
	}
}