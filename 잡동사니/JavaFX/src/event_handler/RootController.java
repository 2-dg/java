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
		btn1.setOnAction((event)->System.out.println("��ư 1"));
		btn2.setOnAction((event)->handleBtn2Display(event));
		
	}
	public void handleBtn2Display(ActionEvent e) {
		System.out.println("��ư 2");
		//�ӽð�ü �ȿ� �� ������ ������ �̷������� ���� �Լ��� ���� �ۼ��Ѵ�.
	}
	
	
//	public void handleBtn1Display(ActionEvent e) {
//		System.out.println("��ư1 Ŭ��");
//	}
//	public void handleBtn2Display(ActionEvent e) {
//		System.out.println("��ư2 Ŭ��");
//	}
}