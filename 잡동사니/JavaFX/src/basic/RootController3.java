package basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController3 implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void handleBtnExit(ActionEvent e) {
		System.out.println("종료입니다.");
		Platform.exit();
	}

}
