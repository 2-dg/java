package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML Button btnExit;
	@FXML Button btnLogin;
	@FXML TextField txtID;
	@FXML PasswordField txtPW;
	@FXML AnchorPane loginWindow;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnExit.setOnAction(event->Platform.exit());
		//나가기
		btnLogin.setOnAction(event->handleBtnLogin());
		//로그인
	}
	public void handleBtnLogin() {
			try {
				Stage mainStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/View/view.fxml"));
				Scene scene = new Scene(root);					
				mainStage.setTitle("TOTO Adminstrator");
				mainStage.setScene(scene);
				mainStage.show();
				Stage window = (Stage)( btnLogin.getScene().getWindow());
				window.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}