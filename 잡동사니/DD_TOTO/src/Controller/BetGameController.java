package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Model.GameVO;
import Model.MemberVO;
import dao.BettingDAO;
import dao.GameDAO;
import dao.MemberDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class BetGameController implements Initializable {
	@FXML private ComboBox comboBGgame;
	@FXML private ComboBox comboBGleague;
	@FXML private ComboBox comboBGstatus;
	@FXML private ComboBox comboBGresult;
	@FXML private ComboBox comboBGchoice;
	
	@FXML private Button btnBGcancle;
	@FXML private Button btnBGbetting;
	@FXML private Button btnBGcal;
	
	@FXML private DatePicker dpBGgamedate;
	
	@FXML private TextField txtBGhomeName;
	@FXML private TextField txtBGawayName;
	@FXML private TextField txtBGwinMoney;
	@FXML private TextField txtBGwinRate;
	@FXML private TextField txtBGdrawMoney;
	@FXML private TextField txtBGdrawRate;
	@FXML private TextField txtBGloseMoney;
	@FXML private TextField txtBGloseRate;
	@FXML private TextField txtBGreward;
	@FXML private TextField txtBGmoney;
	
	@FXML private Label labelBGID;
	@FXML private Label labelBGmoney;
	
	private GameVO game = ClientController.getSelectedGame();
	private MemberVO member = LoginController.getPresentMember();
	private GameDAO gameDAO = new GameDAO();
	private MemberDAO memberDAO = new MemberDAO();
	private BettingDAO bettingDAO = new BettingDAO();
	private int money;
	private double wr;
	private double dr;
	private double lr;
	private String checkID = member.getId();
	private Socket socket=ClientController.getSocket();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//화면 초기화
		initBG();
		//취소버튼 이벤트 등록
		btnBGcancle.setOnAction((event) -> handleBtnCancle());
		//계산버튼 이벤트 등록
		btnBGcal.setOnAction((event) -> handleBtnCal());
		//베팅버튼 이벤트 등록
		btnBGbetting.setOnAction((event) -> handleBtnBetting());
	}
	
	//베팅버튼 이벤트 등록
	public void handleBtnBetting() {
		int check = gameDAO.gameBetting(game.getGameNo(), money, 
				comboBGchoice.getValue().toString(), wr, dr, lr);
		
		bettingDAO.betRegist(game, member, comboBGchoice.getValue().toString(),
				Integer.parseInt(txtBGmoney.getText().toString()), wr, dr, lr);
		
		if(check==1) {
			int userMoney = Integer.parseInt(member.getUserMoney())-Integer.parseInt(txtBGmoney.getText());
			memberDAO.bettedMoney(member.getId(), userMoney);
			int newMoney = Integer.parseInt(member.getUserMoney())-
					Integer.parseInt(txtBGmoney.getText());
			member.setUserMoney(String.valueOf(newMoney));
			Stage stage = (Stage) btnBGbetting.getScene().getWindow();
			stage.close();
			ClientController.listOfGamesInClient.removeAll(ClientController.listOfGamesInClient);
			ClientController.totalListClient();
			ClientController.listOfBettings.removeAll(ClientController.listOfBettings);
			ClientController.totalListClientBet();
			
			member = memberDAO.loginMemeber(checkID);
			send("ID : "+member.getId()+", betted gameNo: "+game.getGameNo()+", bettedMoney: "+Integer.parseInt(txtBGmoney.getText()));
		}
	}

	//계산버튼 이벤트 등록
	public void handleBtnCal() {
		game = gameDAO.gameSelect(game.getGameNo());
		
		int winMoney=0;
		int drawMoney=0;
		int loseMoney=0;
		
		try {
		double total = (game.getWinMoney()+game.getDrawMoney()+game.getLoseMoney())-
				(game.getWinMoney()+game.getDrawMoney()+game.getLoseMoney())*game.getIncomeRate();
		
		if(Integer.parseInt(member.getUserMoney())-Integer.parseInt(txtBGmoney.getText())<0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("경고");
			alert.setContentText("잔고가 모자랍니다.");
			alert.setHeaderText("잔고보다 더 많은 돈을 베팅했는지 확인해주세요");
			alert.showAndWait();
		}else if(comboBGchoice.getValue().toString().equals("승")) {
			winMoney = Integer.parseInt(txtBGmoney.getText())+game.getWinMoney();
			drawMoney = game.getDrawMoney();
			loseMoney = game.getLoseMoney();
			money = winMoney;
			txtBGwinMoney.setText(String.valueOf(winMoney));
			total += Integer.parseInt(txtBGmoney.getText())-
					Double.parseDouble(txtBGmoney.getText())*game.getIncomeRate();
			txtBGreward.setText(String.valueOf((int)total));
		}else if(comboBGchoice.getValue().toString().equals("무")){
			winMoney = game.getWinMoney();
			drawMoney = Integer.parseInt(txtBGmoney.getText())+game.getDrawMoney();
			loseMoney = game.getLoseMoney();
			money = drawMoney;
			txtBGdrawMoney.setText(String.valueOf(drawMoney));
			total += Integer.parseInt(txtBGmoney.getText())-
					Double.parseDouble(txtBGmoney.getText())*game.getIncomeRate();
			txtBGreward.setText(String.valueOf((int)total));
		}else if(comboBGchoice.getValue().toString().equals("패")){
			winMoney = game.getWinMoney();
			drawMoney = game.getDrawMoney();
			loseMoney = Integer.parseInt(txtBGmoney.getText())+game.getLoseMoney();
			money = loseMoney;
			txtBGloseMoney.setText(String.valueOf(loseMoney));
			total += Integer.parseInt(txtBGmoney.getText())-
					Double.parseDouble(txtBGmoney.getText())*game.getIncomeRate();
			txtBGreward.setText(String.valueOf((int)total));
		}	
		
		double winRate = total/winMoney*100;
		wr = total/winMoney;
		double drawRate = total/drawMoney*100;
		dr = total/drawMoney;
		double loseRate = total/loseMoney*100;
		lr = total/loseMoney;
		
		txtBGwinRate.setText(String.valueOf((int)winRate));
		txtBGdrawRate.setText(String.valueOf((int)drawRate));
		txtBGloseRate.setText(String.valueOf((int)loseRate));
		
		btnBGcal.setDisable(true);
		btnBGbetting.setDisable(false);
		comboBGchoice.setDisable(true);
		txtBGmoney.setEditable(false);
		
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("경고");
			alert.setContentText("선택 오류");
			alert.setHeaderText("승무패를 선택해주세요");
			alert.showAndWait();
		}
	}

	//취소버튼 이벤트 등록
	public void handleBtnCancle() {
		Stage stage = (Stage) btnBGcancle.getScene().getWindow();
		stage.close();
	}
	
	//베팅화면 초기화
	public void initBG() {
		try {
			LocalDate localDate = LocalDate.parse(game.getDate());
			comboBGgame.setValue(game.getGame());
			comboBGgame.setEditable(false);
			comboBGleague.setValue(game.getLeague());
			comboBGleague.setEditable(false);
			comboBGstatus.setValue(game.getBetStatus());
			comboBGstatus.setEditable(false);
			comboBGresult.setValue(game.getResult());
			comboBGresult.setEditable(false);
			
			dpBGgamedate.setValue(localDate);
			dpBGgamedate.setEditable(false);
			dpBGgamedate.setDisable(true);
			
			txtBGhomeName.setText(game.getHomeTeam());
			txtBGhomeName.setEditable(false);
			txtBGawayName.setText(game.getAwayTeam());
			txtBGawayName.setEditable(false);
			txtBGwinMoney.setText(String.valueOf(game.getWinMoney()));
			txtBGwinMoney.setEditable(false);
			double winRate = game.getWinRate()*100;
			txtBGwinRate.setText(String.valueOf((int)winRate));
			txtBGwinRate.setEditable(false);
			txtBGdrawMoney.setText(String.valueOf(game.getDrawMoney()));
			txtBGdrawMoney.setEditable(false);
			double drawRate = game.getDrawRate()*100;
			txtBGdrawRate.setText(String.valueOf((int)drawRate));
			txtBGdrawRate.setEditable(false);
			txtBGloseMoney.setText(String.valueOf(game.getLoseMoney()));
			txtBGloseMoney.setEditable(false);
			double loseRate = game.getLoseRate()*100;
			txtBGloseRate.setText(String.valueOf((int)loseRate));
			txtBGloseRate.setEditable(false);
			txtBGreward.setText(String.valueOf((int)game.getReward()));
			txtBGreward.setEditable(false);
			
			labelBGID.setText(member.getId());
			labelBGmoney.setText(String.valueOf("잔고 "+member.getUserMoney())+"원");
			
			ObservableList<String> wdlChoiceList = FXCollections.observableArrayList();
			wdlChoiceList.add("승");
			wdlChoiceList.add("무");
			wdlChoiceList.add("패");
			comboBGchoice.setItems(wdlChoiceList);
			
			btnBGbetting.setDisable(true);
			
			DecimalFormat moneyFormat = new DecimalFormat("###########");
			txtBGmoney.setTextFormatter(new TextFormatter<>(event -> {
				if(event.getControlNewText().isEmpty()) {
					return event;				
				}
				ParsePosition parsePosition = new ParsePosition(0);
				Object object = moneyFormat.parse(event.getControlNewText(),parsePosition);
				 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
						  ||event.getControlNewText().length()==11) {
					  return null;
				  }else {
					  return event;
				  }}));
		} catch (Exception e) {
		}
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

	public void receive() {
		while(true) {
			try {
				byte[] byteErr = new byte[100];
				InputStream is = socket.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				//서버가 비정상적으로 종료됐을 경우 IOException 발생
				int readByteCount = bis.read(byteErr);
				//서버가 정상적으로 socket.close();를 호출했을 경우
				if(readByteCount == -1) { throw new IOException();}
				String data = new String(byteErr, 0, readByteCount, "UTF-8");
				System.out.println("받기 완료 "+data);
			}catch(Exception e) {
				System.out.println("받기 통신 안 됨");
				stopClient();
				break;
			}		
		}
	}
	
	public void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteErr = data.getBytes("UTF-8");
					OutputStream os = socket.getOutputStream();
					BufferedOutputStream bos = new BufferedOutputStream(os);	
					System.out.println(data);
					bos.write(byteErr);
					bos.flush();
					System.out.println("보내기 완료");
				}catch(Exception e) {
					System.out.println("보내기 통신 안 됨");
					stopClient();
				}	
			}		
		};
		thread.start();
	}
	
	public void stopClient() {
		try {
			System.out.println("연결 끊음");
			if(socket!=null&&!socket.isClosed()) {
				socket.close();
			}
		}catch(Exception e) {e.printStackTrace();}
	}
}