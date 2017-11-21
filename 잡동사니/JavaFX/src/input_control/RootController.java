package input_control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController implements Initializable{
	@FXML private TextField txtTitle;
	@FXML private PasswordField txtPW;
	@FXML private DatePicker dateExit;
	@FXML private ComboBox comboPublic;
	@FXML private TextArea txtContent;
	@FXML private Button btnReg;
	@FXML private Button btnCancle;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> comboList = FXCollections.observableArrayList();
		comboList.add("����");
		comboList.add("�����");
		comboPublic.setItems(comboList);
		//FX���� ���� �÷��� �����ӿ�ũ��� ���� ���ϴ�.
	}
	
	public void handleBtnReg() {
		String title = txtTitle.getText();
		String pw = txtPW.getText();
		String tpublic = (String)comboPublic.getValue();
		LocalDate date = dateExit.getValue();
		String datestring = date.toString();
		String txtcont = txtContent.getText();
		
		System.out.println("���� : "+title);
		System.out.println("��й�ȣ : "+pw);
		System.out.println("�������� : "+tpublic);
		System.out.println("��¥ : "+datestring);
		System.out.println("���� : "+txtcont);
		
	}
	public void handleBtnCancle() {
		Platform.exit();
	}
	
	
}
