package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.GameVO;
import Model.MemberVO;
import dao.GameDAO;
import dao.MemberDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class ViewController implements Initializable {
	
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private List<Client> connections = new Vector<Client>();
	
	//���̺�� ������ ���� ��ü ����Ʈ
	public static ObservableList<GameVO> listOfGames = FXCollections.observableArrayList();
	public static ObservableList<MemberVO> listOfMembers = FXCollections.observableArrayList();
	
	//���̺�信�� ���콺�� ���õ� ������ ��üȭ�Ͽ� �ܺ� ��Ʈ�ѷ������� �ٷ� �� �ְ� �������� ����
	private static GameVO selectedGame;
	private static MemberVO selectedMember;
	private TextArea textArea;
	
	// =================��������=================
	@FXML private TableView<GameVO> tableViewGM;
	@FXML private TableView<MemberVO> tableViewMM;
	
	@FXML private DatePicker dpGMdate;

	@FXML private ComboBox comboGMsearch;
	@FXML private ComboBox comboUserSearch;
	@FXML private ComboBox comboUserType;

	@FXML private TextField txtGMhome;
	@FXML private TextField txtGMleague;
	@FXML private TextField txtGMsearch;
	@FXML private TextField txtUserSearch;
	
	@FXML private Button btnGMtotalView;
	@FXML private Button btnGMDetail;
	@FXML private Button btnGMExit;
	@FXML private Button btnGMsearch;
	@FXML private Button btnGMRegist;
	@FXML private Button btnGMEdit;
	@FXML private Button btnGMDelete;
	@FXML private Button btnChatServer1;
	@FXML private Button btnChatServer2;
	// =================�������� ��================
	// =================ȸ��������==================
	@FXML private Button btnUserEdit;  //ȸ������ ���� ��ư
	@FXML private Button btnUserDelete; //ȸ������ ���� ��ư
	@FXML private Button btnUserReg; //ȸ���߰� ��ư
	
	@FXML private TextField txtUserSearchId; //���̵� �˻���
	@FXML private TextField txtUserSearchName; //�̸� �˻���
	@FXML private ComboBox comboUserSearchStatus; // ȸ������ ���ö�
	@FXML private Button btnUserSearch; // ȸ���˻� ��ư
	@FXML private Button btnUserTotal; // ��ü���� ��ư
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//���� ��ŸƮ
		startServer();
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
		//��� �˻� ��ư
		btnGMsearch.setOnAction(event->handleBtnGMSearch(event));
		// ��� ��ü���� ��ư
		btnGMtotalView.setOnAction(event->handleBtnTotal(event));
		// ä�ù�ư �̺�Ʈ
		btnChatServer1.setOnAction((event)-> handleBtnChat(event));
		btnChatServer2.setOnAction((event)-> handleBtnChat(event));
		// �˻��޺��ڽ� ����
		comboGMsearch.setOnAction(event->handleComboGameSearch(event));
		
		// ==============�������� ���� ��=======================
		// ==============ȸ�������� ���� ����======================
		//ȸ�������� �ʱ�ȭ
		initMM();
		//���̺�� �̺�Ʈ
		tableViewMM.setOnMouseClicked((event)-> handleUserTableView(event));
		// ȸ������ ���� ��ư
		btnUserEdit.setOnAction((event)->handleBtnUserEdit(event));
		// ȸ������ ���� ��ư
		btnUserDelete.setOnAction(event->handleBtnUserDelete());
		// ȸ���߰� ��ư
		btnUserReg.setOnAction(event->handleBtnUserReg(event));
		// ���� �˻� ��ư
		btnUserSearch.setOnAction(event->handleBtnUserSearch(event));
		// ��ü���� ��ư
		btnUserTotal.setOnAction(event->handleBtnUserTotal(event));
		//�˻� �޺��ڽ� ����
		comboUserSearch.setOnAction(event->handleComboUserSearch(event));
		
	}//END OF INITIALIZE
	
	private void handleComboUserSearch(Event event) {
		if(comboUserSearch.getSelectionModel().getSelectedItem()=="���̵�"){
			comboUserType.setDisable(true);
			txtUserSearch.setDisable(false);
			txtUserSearch.clear();
		}else if(comboUserSearch.getSelectionModel().getSelectedItem()=="�̸�"){
			comboUserType.setDisable(true);
			txtUserSearch.setDisable(false);
			txtUserSearch.clear();
		}else if(comboUserSearch.getSelectionModel().getSelectedItem()=="����"){
			txtUserSearch.setDisable(true);
			comboUserType.setDisable(false);
			txtUserSearch.clear();
		}
	}

	private void handleComboGameSearch(Event event) {
		
		if(comboGMsearch.getSelectionModel().getSelectedItem()=="��� ��¥"){
			txtGMsearch.setDisable(true);
			dpGMdate.setDisable(false);
			txtGMsearch.clear();
		}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="��� ����"){
			dpGMdate.setValue(LocalDate.now());
			dpGMdate.setDisable(true);
			txtGMsearch.setDisable(false);
			txtGMsearch.clear();
		}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="���׸�"){
			dpGMdate.setValue(LocalDate.now());
			dpGMdate.setDisable(true);
			txtGMsearch.setDisable(false);
			txtGMsearch.clear();
		}
	}

	public void handleBtnChat(ActionEvent event) {
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			Parent root = FXMLLoader.load(getClass().getResource("/View/ViewChatting.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("����ڿ� ä��");
			stage.setScene(scene);
			stage.setAlwaysOnTop(true);
			stage.show();
			textArea = (TextArea) root.lookup("#txtAserver");
			TextField textField = (TextField) root.lookup("#txtFserver");
			Button btnChatExit = (Button) root.lookup("#btnChatExit");
			Button btnChatSend = (Button) root.lookup("#btnChatSend");
			textArea.setEditable(false);
			
			btnChatSend.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String sendMessage = "��� : "+textField.getText();
					textArea.appendText(sendMessage+"\n");
					for(Client client : connections) {
						client.send("c"+sendMessage); 
					}
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
			e.printStackTrace();
		}
	}

	protected void send() {
	}

	public void handleBtnTotal(ActionEvent event) {
		listOfGames.removeAll(listOfGames);
		totalListGM();
	}

	public void handleBtnUserTotal(ActionEvent event) {
		listOfMembers.removeAll(listOfMembers);
		totalListMM();
	}

	public void handleBtnUserSearch(ActionEvent event) {
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = null;//����Ÿ���̽� ����
		String searchName="";
		String searchId="";
		String searchType="";
		
		String search="";
		boolean searchResult = false;
		try {
			searchName = txtUserSearch.getText().toString();			
			searchId = txtUserSearch.getText().toString();				
			
			memberDAO = new MemberDAO();
			if(comboUserSearch.getSelectionModel().getSelectedItem().toString().equals("���̵�")){				
				search=searchId;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//�� ���ڵ带  ArrayList<MemberVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (memberVO.getId().equals(search)) {
						txtUserSearch.clear();
						listOfMembers.removeAll(listOfMembers);
						for (int index = 0; index < rowCount; index++) {
							memberVO = list.get(index);
							if (memberVO.getId().equals(search)) {
								listOfMembers.add(memberVO);
								searchResult = true;
								}
							}
						}
					}	
				}	
				
			}else if(comboUserSearch.getSelectionModel().getSelectedItem().equals("�̸�")){
				search=searchName;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				//�����̸��� �˻��ؼ� �ش�� ���ڵ峻���� memberVO�� �����ؼ� ��������
				
			if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<String> title;
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//�� ���ڵ带  ArrayList<GameVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (memberVO.getName().equals(search)) {
						txtUserSearch.clear();
						listOfMembers.removeAll(listOfMembers);
						for (int index = 0; index < rowCount; index++) {
							memberVO = list.get(index);
							if (memberVO.getName().equals(search)) {
								listOfMembers.add(memberVO);
								searchResult = true;
								}
							}
						}
					}	
				}		
				
			}else if(comboUserSearch.getSelectionModel().getSelectedItem().equals("����")){
				searchType = comboUserType.getValue().toString();		
				search=searchType;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//�� ���ڵ带  ArrayList<MemberVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (memberVO.getUserType().equals(search)) {
						txtUserSearch.clear();
						listOfMembers.removeAll(listOfMembers);
						for (int index = 0; index < rowCount; index++) {
							memberVO = list.get(index);
							if (memberVO.getUserType().equals(search)) {
								listOfMembers.add(memberVO);
								searchResult = true;
								}
							}
						}
					}	
				}		
				
			}
			if (search.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ���� �˻�");
				alert.setHeaderText("������ �Է����ּ���.");
				alert.setContentText("�˻� ����");				
				alert.showAndWait();
			}
				
			if (!searchResult) {
				dpGMdate.setValue(LocalDate.now());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ���� �˻�");
				alert.setHeaderText(search + " �Է��� ������ ����Ʈ�� �������� �ʽ��ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
				txtUserSearch.clear();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���� �˻� ����");
			alert.setHeaderText("���� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�������� �˻� ����");
		}
	}
	
	
	
	
	
	
	private void handleBtnUserReg(ActionEvent event) {
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnUserReg.getScene().getWindow());
			Parent root = FXMLLoader.load(getClass().getResource("/View/RegUser.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("ȸ�� �߰�");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void handleBtnUserDelete() {
		MemberDAO memberDAO= null;
	      MemberVO memberVO = tableViewMM.getSelectionModel().getSelectedItem();
	      memberDAO = new MemberDAO();
	      memberDAO.memberDelete(memberVO.getId());
	      listOfMembers.removeAll(listOfMembers);
	      totalListMM();
	}
	
	private void handleUserTableView(MouseEvent event) {
		if(event.getClickCount()==1) {		
			selectedMember = tableViewMM.getSelectionModel().getSelectedItem();
		}
	}
	
	private void handleBtnUserEdit(ActionEvent event) {
		try {
			 Parent root1 = FXMLLoader.load(getClass().getResource("/View/EditUser.fxml"));
	         Stage stage = new Stage(StageStyle.UTILITY);
	         stage.initModality(Modality.WINDOW_MODAL);
	         stage.initOwner(btnUserEdit.getScene().getWindow());
	         stage.setTitle("ȸ������ ����");
	         Scene scene = new Scene(root1);
	         stage.setScene(scene);
	         stage.setResizable(false);
	         stage.show();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}
	
	public void handleBtnGMSearch(ActionEvent event) {
		GameVO gameVO = new GameVO();
		GameDAO gameDAO = null;//����Ÿ���̽� ����
		String searchDate="";
		String searchGame="";
		String searchLeague="";
		
		String search="";
		
		boolean searchResult = false;
		try {
			searchDate = dpGMdate.getValue().toString();			
			searchGame = txtGMsearch.getText().toString();			
			searchLeague = txtGMsearch.getText().toString();			
				
			gameDAO = new GameDAO();
			if(comboGMsearch.getSelectionModel().getSelectedItem()=="��� ��¥"){
				search=searchDate;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());
				//�����̸��� �˻��ؼ� �ش�� ���ڵ峻���� memberVO�� �����ؼ� ��������
				
			if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//�ʵ�� �̸��� ����
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//�� ���ڵ带  ArrayList<GameVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (gameVO.getDate().equals(search)) {
						txtGMsearch.clear();
						listOfGames.removeAll(listOfGames);
						for (int index = 0; index < rowCount; index++) {
							gameVO = list.get(index);
							if (gameVO.getDate().equals(search)) {
								listOfGames.add(gameVO);
								searchResult = true;
								}
							}
						}
					}	
				}		
				
			}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="��� ����"){
				search=searchGame;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());
				
				if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//�ʵ�� �̸��� ����
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//�� ���ڵ带  ArrayList<MemberVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (gameVO.getGame().equals(search)) {
						txtGMsearch.clear();
						listOfGames.removeAll(listOfGames);
						for (int index = 0; index < rowCount; index++) {
							gameVO = list.get(index);
							if (gameVO.getGame().equals(search)) {
								listOfGames.add(gameVO);
								searchResult = true;
								}
							}
						}
					}	
				}	
				
			}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="���׸�"){
				search=searchLeague;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());

				if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//�ʵ�� �̸��� ����
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//�� ���ڵ带  ArrayList<MemberVO> �������� �����ؼ� ������.
						int rowCount = list.size();
					if (gameVO.getLeague().equals(search)) {
						txtGMsearch.clear();
						listOfGames.removeAll(listOfGames);
						for (int index = 0; index < rowCount; index++) {
							gameVO = list.get(index);
							if (gameVO.getLeague().equals(search)) {
								listOfGames.add(gameVO);
								searchResult = true;
								}
							}
						}
					}	
				}		
				
			}
			if (search.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ���� �˻�");
				alert.setHeaderText("������ �Է����ּ���.");
				alert.setContentText("�˻� ����");				
				alert.showAndWait();
			}
				
			if (!searchResult) {
				dpGMdate.setValue(LocalDate.now());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ���� �˻�");
				alert.setHeaderText(searchDate + " �Է��� ������ ����Ʈ�� �������� �ʽ��ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
				txtGMsearch.clear();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���� �˻� ����");
			alert.setHeaderText("���� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�������� �˻� ����");
		}
	}
	
	//ȸ�������� �ʱ�ȭ
	private void initMM() {
		
		  totalListMM();
		  
		  ObservableList<String> userSearchList = FXCollections.observableArrayList();
		  userSearchList.add("���̵�");
		  userSearchList.add("�̸�");
		  userSearchList.add("����");
		  comboUserSearch.setItems(userSearchList);

		  ObservableList<String> userTypeList = FXCollections.observableArrayList();
		  userTypeList.add("�Ϲ�ȸ��");
		  userTypeList.add("������");
		  userTypeList.add("������Ʈ");
		  comboUserType.setItems(userTypeList);
			
		  TableColumn tcId = new TableColumn("���̵�");
	      tcId.setPrefWidth(90);
	      tcId.setCellValueFactory(new PropertyValueFactory<>("id"));      
	      
	      TableColumn tcName = new TableColumn("�̸�");
	      tcName.setPrefWidth(90);
	      tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
	      
	      TableColumn tcPhoneNum = new TableColumn("��ȭ��ȣ");
	      tcPhoneNum.setPrefWidth(120);
	      tcPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	      
	      TableColumn tcBankName = new TableColumn("�����");
	      tcBankName.setPrefWidth(90);
	      tcBankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
	      
	      TableColumn tcAccountNum = new TableColumn("���¹�ȣ");
	      tcAccountNum.setPrefWidth(200);
	      tcAccountNum.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
	      
	      TableColumn tcAccountHolder = new TableColumn("������");
	      tcAccountHolder.setPrefWidth(60);
	      tcAccountHolder.setCellValueFactory(new PropertyValueFactory<>("accountHolder"));
	      
	      TableColumn tcUserType = new TableColumn("����");
	      tcUserType.setPrefWidth(70);
	      tcUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
	      
	      TableColumn tcUserMoney = new TableColumn("�����Ӵ�");
	      tcUserMoney.setPrefWidth(120);
	      tcUserMoney.setCellValueFactory(new PropertyValueFactory<>("userMoney"));
	      
	      TableColumn tcUserStatus = new TableColumn("���ӻ���");
	      tcUserStatus.setPrefWidth(120);
	      tcUserStatus.setCellValueFactory(new PropertyValueFactory<>("userStatus"));
	      
	      tableViewMM.getColumns().addAll(tcId, tcName, tcPhoneNum, tcBankName, 
	            tcAccountNum, tcAccountHolder, tcUserType, tcUserMoney, tcUserStatus);

	      tableViewMM.setItems(listOfMembers);
		
	}
	
	//ȸ�������� ���̺�� �ʱ�ȭ
	public static void totalListMM() {
	      MemberDAO memberDB = new MemberDAO();
	      MemberVO joinMember = new MemberVO();

	      ArrayList<MemberVO> list;

	      list = memberDB.getMemberTotal();
	      for (int index = 0; index < list.size(); index++) {
	         joinMember = list.get(index);
	         listOfMembers.add(joinMember);
	      }
	}

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
	      GameDAO gameDAO= null;
	      GameVO gameVO = tableViewGM.getSelectionModel().getSelectedItem();
	      gameDAO = new GameDAO();
	      gameDAO.gameDelete(gameVO.getGameNo());
	      listOfGames.removeAll(listOfGames);
	      totalListGM();
	}
	
	//��Ʈ�ѷ� �и��� ���� ��ü ���� �޼ҵ�
	public static GameVO getSelectedGame() {
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
	public static void totalListGM() {
		GameDAO gameDAO = new GameDAO();
		ArrayList<GameVO> list = gameDAO.getGameTotal();
		for(GameVO game : list) {
			try {
				listOfGames.add(game);
			} catch (Exception e) {
			}
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
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���");
				alert.setContentText("��⸦ �������ֽʽÿ�");
				alert.setHeaderText("��������� ����� ���õ��� �ʾҽ��ϴ�.");
				alert.showAndWait();
	      }
	   }	
	
	//���Ӱ���ȭ�� �ʱ�ȭ
	public void initGM() {
		//����Ʈ��Ŀ ����
		dpGMdate.setValue(LocalDate.now());		
		dpGMdate.setEditable(false);
		
		ObservableList <String> gameSearchList=FXCollections.observableArrayList();
		gameSearchList.add("��� ��¥");
		gameSearchList.add("��� ����");
		gameSearchList.add("���׸�");
		comboGMsearch.setItems(gameSearchList);
		
		//DB���� ����� �о�� ��üȭ
		totalListGM();
		
		TableColumn tcNo = new TableColumn("No");
		tcNo.setPrefWidth(30);
		tcNo.setStyle("-fx-alignment: CENTER");
		tcNo.setCellValueFactory(new PropertyValueFactory<>("gameNo"));
		
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
		
		tableViewGM.getColumns().addAll(tcNo, tcDate, tcGame, tcLeague, tcHome, tcAway, tcICrate, tcWinRate, tcDrawRate,
				tcLoseRate, tcBetStatus, tcResult);
		
		tableViewGM.setItems(listOfGames);
	}
	
	public void startServer() {
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors());
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 7777));
		}catch(Exception e) {
			if(!serverSocket.isClosed()) {stopServer();}
		}
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("��������");
				while(true) {
					try {						
						Socket socket = serverSocket.accept();
						String messege = "[���� ����: " + socket.getRemoteSocketAddress()  + ": " + Thread.currentThread().getName() + "]";
						System.out.println(messege);
						Client client = new Client(socket);
						connections.add(client);						
						System.out.println("[���� ����: " + connections.size() + "]");
					}catch(Exception e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
					}				
				}
			}
		};
		executorService.submit(runnable);
	}

	public void stopServer() {
		try {
			Iterator<Client> iterator = connections.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket!=null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if(executorService!=null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("���� ����");
			System.exit(0);
		}catch(Exception e) {
		}
	}
	
	private class Client {
		Socket socket;
		Client(Socket socket){
			this.socket = socket;
			receive();
		}//end of Client
		void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							byte[] byteArr = new byte[100];
							InputStream inputStream = socket.getInputStream();
							int readByteCount = inputStream.read(byteArr);
							if(readByteCount == -1) {  throw new IOException(); }
							String message = "[��û ó��: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
							System.out.println(message);
							String data = new String(byteArr, 0, readByteCount, "UTF-8");
							System.out.println(data);
							if(data.charAt(0)=='c') {
								data = data.substring(1, data.length());
								textArea.appendText(data+"\n");								
							}
							for(Client client : connections) {
								client.send(data); 
							}
							
						}
					} catch(Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			executorService.submit(runnable);
		}
		void send(String data) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
//						System.out.println(data);
						outputStream.write(byteArr);
						outputStream.flush();
					} catch(Exception e) {
						try {
							String message = "[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			executorService.submit(runnable);
		}
	}
	public static MemberVO getselectedMember(){
		return selectedMember;
	}
}