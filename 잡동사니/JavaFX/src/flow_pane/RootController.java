package flow_pane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	public void handleBtnInsert(ActionEvent e) {
		System.out.println("��ư �Է� ����");
		Platform.exit();
		
	}
	public void handleButtonExit(ActionEvent e) {
		System.out.println("��ư �Է� ����");
		Platform.exit();
		
	}
	
}