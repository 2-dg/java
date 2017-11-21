package event_handler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn1.setOnAction((event)->System.out.println("버튼 1"));
		btn2.setOnAction((event)->handleBtn2Display(event));
		
	}
	public void handleBtn2Display(ActionEvent e) {
		System.out.println("버튼 2");
		//임시객체 안에 들어갈 구문이 많으면 이런식으로 따로 함수를 만들어서 작성한다.
	}
	
	
//	public void handleBtn1Display(ActionEvent e) {
//		System.out.println("버튼1 클릭");
//	}
//	public void handleBtn2Display(ActionEvent e) {
//		System.out.println("버튼2 클릭");
//	}
}