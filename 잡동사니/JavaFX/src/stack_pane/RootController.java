package stack_pane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RootController implements Initializable{
	@FXML private AnchorPane anchorPane;		
	@FXML private BorderPane borderPane;
	//이걸 쓰면 객체가 되어 컨트롤이 가능해진다. 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void handleBtn1SceneChange(ActionEvent e) {
		anchorPane.setVisible(false);
		System.out.println("화면전환");
		borderPane.setVisible(true);
	}
	public void handleBtn2SceneChange(ActionEvent e) {
		borderPane.setVisible(false);
		System.out.println("화면전환");
		anchorPane.setVisible(true);
	}
}
