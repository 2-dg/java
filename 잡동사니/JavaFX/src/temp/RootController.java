package temp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void handleBtnExit(ActionEvent e) {
		System.out.println("Á¾·á");
		Platform.exit();
	}

}
