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
	//�̰� ���� ��ü�� �Ǿ� ��Ʈ���� ����������. 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void handleBtn1SceneChange(ActionEvent e) {
		anchorPane.setVisible(false);
		System.out.println("ȭ����ȯ");
		borderPane.setVisible(true);
	}
	public void handleBtn2SceneChange(ActionEvent e) {
		borderPane.setVisible(false);
		System.out.println("ȭ����ȯ");
		anchorPane.setVisible(true);
	}
}
