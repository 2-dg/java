package basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController2 implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}
	
	public void handleBtnExit(ActionEvent e) {
		System.out.println("�����");
		Platform.exit();
	}
}
