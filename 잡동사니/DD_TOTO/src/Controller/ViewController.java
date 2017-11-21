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
	
	//테이블뷰 설정을 위한 객체 리스트
	public static ObservableList<GameVO> listOfGames = FXCollections.observableArrayList();
	public static ObservableList<MemberVO> listOfMembers = FXCollections.observableArrayList();
	
	//테이블뷰에서 마우스로 선택된 게임을 객체화하여 외부 컨트롤러에서도 다룰 수 있게 정적으로 선언
	private static GameVO selectedGame;
	private static MemberVO selectedMember;
	private TextArea textArea;
	
	// =================경기관리탭=================
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
	// =================경기관리탭 끝================
	// =================회원관리텝==================
	@FXML private Button btnUserEdit;  //회원정보 수정 버튼
	@FXML private Button btnUserDelete; //회원정보 삭제 버튼
	@FXML private Button btnUserReg; //회원추가 버튼
	
	@FXML private TextField txtUserSearchId; //아이디 검색란
	@FXML private TextField txtUserSearchName; //이름 검색란
	@FXML private ComboBox comboUserSearchStatus; // 회원상태 선택란
	@FXML private Button btnUserSearch; // 회원검색 버튼
	@FXML private Button btnUserTotal; // 전체보기 버튼
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//서버 스타트
		startServer();
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
		//경기 검색 버튼
		btnGMsearch.setOnAction(event->handleBtnGMSearch(event));
		// 경기 전체보기 버튼
		btnGMtotalView.setOnAction(event->handleBtnTotal(event));
		// 채팅버튼 이벤트
		btnChatServer1.setOnAction((event)-> handleBtnChat(event));
		btnChatServer2.setOnAction((event)-> handleBtnChat(event));
		// 검색콤보박스 설정
		comboGMsearch.setOnAction(event->handleComboGameSearch(event));
		
		// ==============경기관리탭 설정 끝=======================
		// ==============회원관리탭 설정 시작======================
		//회원관리탭 초기화
		initMM();
		//테이블뷰 이벤트
		tableViewMM.setOnMouseClicked((event)-> handleUserTableView(event));
		// 회원정보 수정 버튼
		btnUserEdit.setOnAction((event)->handleBtnUserEdit(event));
		// 회원정보 삭제 버튼
		btnUserDelete.setOnAction(event->handleBtnUserDelete());
		// 회원추가 버튼
		btnUserReg.setOnAction(event->handleBtnUserReg(event));
		// 유저 검색 버튼
		btnUserSearch.setOnAction(event->handleBtnUserSearch(event));
		// 전체보기 버튼
		btnUserTotal.setOnAction(event->handleBtnUserTotal(event));
		//검색 콤보박스 설정
		comboUserSearch.setOnAction(event->handleComboUserSearch(event));
		
	}//END OF INITIALIZE
	
	private void handleComboUserSearch(Event event) {
		if(comboUserSearch.getSelectionModel().getSelectedItem()=="아이디"){
			comboUserType.setDisable(true);
			txtUserSearch.setDisable(false);
			txtUserSearch.clear();
		}else if(comboUserSearch.getSelectionModel().getSelectedItem()=="이름"){
			comboUserType.setDisable(true);
			txtUserSearch.setDisable(false);
			txtUserSearch.clear();
		}else if(comboUserSearch.getSelectionModel().getSelectedItem()=="권한"){
			txtUserSearch.setDisable(true);
			comboUserType.setDisable(false);
			txtUserSearch.clear();
		}
	}

	private void handleComboGameSearch(Event event) {
		
		if(comboGMsearch.getSelectionModel().getSelectedItem()=="경기 날짜"){
			txtGMsearch.setDisable(true);
			dpGMdate.setDisable(false);
			txtGMsearch.clear();
		}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="경기 종목"){
			dpGMdate.setValue(LocalDate.now());
			dpGMdate.setDisable(true);
			txtGMsearch.setDisable(false);
			txtGMsearch.clear();
		}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="리그명"){
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
			stage.setTitle("사용자와 채팅");
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
					String sendMessage = "운영자 : "+textField.getText();
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
		MemberDAO memberDAO = null;//데이타베이스 연결
		String searchName="";
		String searchId="";
		String searchType="";
		
		String search="";
		boolean searchResult = false;
		try {
			searchName = txtUserSearch.getText().toString();			
			searchId = txtUserSearch.getText().toString();				
			
			memberDAO = new MemberDAO();
			if(comboUserSearch.getSelectionModel().getSelectedItem().toString().equals("아이디")){				
				search=searchId;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//총 레코드를  ArrayList<MemberVO> 형식으로 저장해서 리턴함.
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
				
			}else if(comboUserSearch.getSelectionModel().getSelectedItem().equals("이름")){
				search=searchName;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				//유저이름을 검색해서 해당된 레코드내용을 memberVO에 저장해서 리턴해줌
				
			if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<String> title;
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//총 레코드를  ArrayList<GameVO> 형식으로 저장해서 리턴함.
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
				
			}else if(comboUserSearch.getSelectionModel().getSelectedItem().equals("권한")){
				searchType = comboUserType.getValue().toString();		
				search=searchType;
				memberVO = memberDAO.memberSearch(search, comboUserSearch.getSelectionModel().getSelectedItem().toString());
				if(!search.equals("")){
					if (memberVO != null) {
						ArrayList<MemberVO> list;
						list = memberDAO.getMemberTotal();//총 레코드를  ArrayList<MemberVO> 형식으로 저장해서 리턴함.
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
				alert.setTitle("유저 정보 검색");
				alert.setHeaderText("정보를 입력해주세요.");
				alert.setContentText("검색 오류");				
				alert.showAndWait();
			}
				
			if (!searchResult) {
				dpGMdate.setValue(LocalDate.now());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("유저 정보 검색");
				alert.setHeaderText(search + " 입력한 정보가 리스트에 존재하지 않습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
				txtUserSearch.clear();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("유저 정보 검색 오류");
			alert.setHeaderText("유저 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("유저정보 검색 오류");
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
			stage.setTitle("회원 추가");
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
	         stage.setTitle("회원정보 수정");
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
		GameDAO gameDAO = null;//데이타베이스 연결
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
			if(comboGMsearch.getSelectionModel().getSelectedItem()=="경기 날짜"){
				search=searchDate;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());
				//유저이름을 검색해서 해당된 레코드내용을 memberVO에 저장해서 리턴해줌
				
			if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//필드명 이름들 저장
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//총 레코드를  ArrayList<GameVO> 형식으로 저장해서 리턴함.
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
				
			}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="경기 종목"){
				search=searchGame;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());
				
				if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//필드명 이름들 저장
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//총 레코드를  ArrayList<MemberVO> 형식으로 저장해서 리턴함.
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
				
			}else if(comboGMsearch.getSelectionModel().getSelectedItem()=="리그명"){
				search=searchLeague;
				gameVO = gameDAO.gameSearch(search, comboGMsearch.getSelectionModel().getSelectedItem().toString());

				if(!search.equals("")){
					if (gameVO != null) {
						ArrayList<String> title;
						ArrayList<GameVO> list;
						title = gameDAO.getColumnName();//필드명 이름들 저장
						int columnCount = title.size();
						list = gameDAO.getGameTotal();//총 레코드를  ArrayList<MemberVO> 형식으로 저장해서 리턴함.
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
				alert.setTitle("유저 정보 검색");
				alert.setHeaderText("정보를 입력해주세요.");
				alert.setContentText("검색 오류");				
				alert.showAndWait();
			}
				
			if (!searchResult) {
				dpGMdate.setValue(LocalDate.now());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("유저 정보 검색");
				alert.setHeaderText(searchDate + " 입력한 정보가 리스트에 존재하지 않습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
				txtGMsearch.clear();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("유저 정보 검색 오류");
			alert.setHeaderText("유저 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("유저정보 검색 오류");
		}
	}
	
	//회원관리탭 초기화
	private void initMM() {
		
		  totalListMM();
		  
		  ObservableList<String> userSearchList = FXCollections.observableArrayList();
		  userSearchList.add("아이디");
		  userSearchList.add("이름");
		  userSearchList.add("권한");
		  comboUserSearch.setItems(userSearchList);

		  ObservableList<String> userTypeList = FXCollections.observableArrayList();
		  userTypeList.add("일반회원");
		  userTypeList.add("관리자");
		  userTypeList.add("블랙리스트");
		  comboUserType.setItems(userTypeList);
			
		  TableColumn tcId = new TableColumn("아이디");
	      tcId.setPrefWidth(90);
	      tcId.setCellValueFactory(new PropertyValueFactory<>("id"));      
	      
	      TableColumn tcName = new TableColumn("이름");
	      tcName.setPrefWidth(90);
	      tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
	      
	      TableColumn tcPhoneNum = new TableColumn("전화번호");
	      tcPhoneNum.setPrefWidth(120);
	      tcPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	      
	      TableColumn tcBankName = new TableColumn("은행명");
	      tcBankName.setPrefWidth(90);
	      tcBankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
	      
	      TableColumn tcAccountNum = new TableColumn("계좌번호");
	      tcAccountNum.setPrefWidth(200);
	      tcAccountNum.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
	      
	      TableColumn tcAccountHolder = new TableColumn("예금주");
	      tcAccountHolder.setPrefWidth(60);
	      tcAccountHolder.setCellValueFactory(new PropertyValueFactory<>("accountHolder"));
	      
	      TableColumn tcUserType = new TableColumn("권한");
	      tcUserType.setPrefWidth(70);
	      tcUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
	      
	      TableColumn tcUserMoney = new TableColumn("유저머니");
	      tcUserMoney.setPrefWidth(120);
	      tcUserMoney.setCellValueFactory(new PropertyValueFactory<>("userMoney"));
	      
	      TableColumn tcUserStatus = new TableColumn("접속상태");
	      tcUserStatus.setPrefWidth(120);
	      tcUserStatus.setCellValueFactory(new PropertyValueFactory<>("userStatus"));
	      
	      tableViewMM.getColumns().addAll(tcId, tcName, tcPhoneNum, tcBankName, 
	            tcAccountNum, tcAccountHolder, tcUserType, tcUserMoney, tcUserStatus);

	      tableViewMM.setItems(listOfMembers);
		
	}
	
	//회원관리탭 테이블뷰 초기화
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
	      GameDAO gameDAO= null;
	      GameVO gameVO = tableViewGM.getSelectionModel().getSelectedItem();
	      gameDAO = new GameDAO();
	      gameDAO.gameDelete(gameVO.getGameNo());
	      listOfGames.removeAll(listOfGames);
	      totalListGM();
	}
	
	//컨트롤러 분리를 위한 객체 전달 메소드
	public static GameVO getSelectedGame() {
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
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("경고");
				alert.setContentText("경기를 선택해주십시오");
				alert.setHeaderText("경기정보가 제대로 선택되지 않았습니다.");
				alert.showAndWait();
	      }
	   }	
	
	//게임관리화면 초기화
	public void initGM() {
		//데이트픽커 설정
		dpGMdate.setValue(LocalDate.now());		
		dpGMdate.setEditable(false);
		
		ObservableList <String> gameSearchList=FXCollections.observableArrayList();
		gameSearchList.add("경기 날짜");
		gameSearchList.add("경기 종목");
		gameSearchList.add("리그명");
		comboGMsearch.setItems(gameSearchList);
		
		//DB에서 목록을 읽어와 객체화
		totalListGM();
		
		TableColumn tcNo = new TableColumn("No");
		tcNo.setPrefWidth(30);
		tcNo.setStyle("-fx-alignment: CENTER");
		tcNo.setCellValueFactory(new PropertyValueFactory<>("gameNo"));
		
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
				System.out.println("서버시작");
				while(true) {
					try {						
						Socket socket = serverSocket.accept();
						String messege = "[연결 수락: " + socket.getRemoteSocketAddress()  + ": " + Thread.currentThread().getName() + "]";
						System.out.println(messege);
						Client client = new Client(socket);
						connections.add(client);						
						System.out.println("[연결 개수: " + connections.size() + "]");
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
			System.out.println("서버 종료");
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
							String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
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
							String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
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
							String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
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