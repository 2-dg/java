package basic;

import java.lang.management.PlatformManagedObject;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class RootController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	public void handleBoxCheck(ActionEvent event) {
		System.out.println("확인버튼을 눌렀습니다.");
		Platform.exit();
		
	}

}
