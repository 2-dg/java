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
		comboList.add("공개");
		comboList.add("비공개");
		comboPublic.setItems(comboList);
		//FX에서 쓰는 컬렉션 프레임워크라고 보면 편하다.
	}
	
	public void handleBtnReg() {
		String title = txtTitle.getText();
		String pw = txtPW.getText();
		String tpublic = (String)comboPublic.getValue();
		LocalDate date = dateExit.getValue();
		String datestring = date.toString();
		String txtcont = txtContent.getText();
		
		System.out.println("제목 : "+title);
		System.out.println("비밀번호 : "+pw);
		System.out.println("공개여부 : "+tpublic);
		System.out.println("날짜 : "+datestring);
		System.out.println("내용 : "+txtcont);
		
	}
	public void handleBtnCancle() {
		Platform.exit();
	}
	
	
}
