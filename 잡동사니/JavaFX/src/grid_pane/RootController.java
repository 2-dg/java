package grid_pane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void handleBtnCancle(ActionEvent e) {
		System.out.println("½ÇÇà");
		Platform.exit();
	}

}
