package temp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController2 implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void handleBtnNext(ActionEvent e) {
		System.out.println("종료종료");
		Platform.exit();
	}

}
