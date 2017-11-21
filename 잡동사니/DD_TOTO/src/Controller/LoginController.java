package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Model.MemberVO;
import application.DBUtil;
import dao.MemberDAO;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable {
	
	@FXML Button btnExit;
	@FXML Button btnLogin;
	@FXML TextField txtID;
	@FXML PasswordField txtPW;
	@FXML AnchorPane loginWindow;
	@FXML Button btnJoinMember;
	
	private static MemberVO member;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnExit.setOnAction(event -> Platform.exit());
		// 나가기
		btnLogin.setOnAction(event -> handleBtnLogin());
		// 로그인
		btnJoinMember.setOnAction(event -> handleBtnJoinMember());
		// 회원가입창
	}

	protected void handleBtnJoinMember() {
		try {
			Stage mainStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/View/JoinMember.fxml"));
			Scene scene = new Scene(root);
			mainStage.setTitle("TOTO JoinMember");
			mainStage.setScene(scene);
			mainStage.show();
			Stage window = (Stage) (btnJoinMember.getScene().getWindow());
			window.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleBtnLogin() {
		String id = txtID.getText().trim();
		String pw = txtPW.getText().trim();
		String sql = "select id, pw1, userType, userStatus " + "from dd_joinmember where id=? and pw1=?";
		String typeCheck;
		String statusCheck;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
				typeCheck = rs.getString("userType");
				statusCheck = rs.getString("userStatus");
				if (statusCheck.equals("접속중")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("접속중");
					alert.setHeaderText("이미 접속중인 아이디입니다");
					alert.setContentText("관리자에게 문의하세요");
					alert.showAndWait();
				} else if (typeCheck.equals("관리자")) {
					MemberVO thisMember = new MemberDAO().loginMemeber(id);
					member = thisMember;
					System.out.println("관리자 " + id + " : 로그인");
					Stage mainStage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/view.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root);
					mainStage.setTitle("DDTOTO Adminstrator");
					mainStage.setScene(scene);
					mainStage.show();
					Stage window = (Stage) (btnLogin.getScene().getWindow());
					window.close();
					mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent event) {
							System.out.println("서버 종료");
							MemberDAO memberDAO = new MemberDAO();
							memberDAO.logoutMemeber(member.getId());
							Platform.exit();
							System.exit(0);
						}
					});
				} else if (typeCheck.equals("일반회원")) {
					MemberVO thisMember = new MemberDAO().loginMemeber(id);
					member = thisMember;
					System.out.println("일반회원 " + id + " : 로그인");
					Stage mainStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/View/Client.fxml"));
					Scene scene = new Scene(root);
					mainStage.setTitle(id+" 접속. Welcome to DDTOTO");
					mainStage.setScene(scene);
					mainStage.show();
					Stage window = (Stage) (btnLogin.getScene().getWindow());
					window.close();
					mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent event) {
							MemberDAO memberDAO = new MemberDAO();
							memberDAO.logoutMemeber(member.getId());
						}
					});
				} else if (typeCheck.equals("블랙리스트")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("접근금지");
					alert.setHeaderText("블랙리스트로 등록된 아이디입니다");
					alert.setContentText("관리자에게 문의하세요");
					alert.showAndWait();
				}
			} else {
				System.out.println("Check");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("로그인 오류");
				alert.setHeaderText("로그인이 불가능합니다");
				alert.setContentText("아이디와 비밀번호를 확인하세요");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberVO getPresentMember() {
		return member;
	}
}