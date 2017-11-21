package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import Model.MemberVO;
import dao.MemberDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class JoinMemberController implements Initializable{
	
	@FXML TextField txtMbName;
	@FXML TextField txtMbId;
	@FXML TextField txtMbPw1;
	@FXML TextField txtMbPw2;
	@FXML ComboBox comboMbPhone1;
	@FXML TextField txtMbPhone2;
	@FXML TextField txtMbPhone3;
	@FXML ComboBox comboMbBankName;
	@FXML TextField txtMbAccount;
	@FXML TextField txtMbAccountHolder;
	@FXML Button btnMbIdConfirm;
	@FXML Button btnMbConfirm;
	@FXML Button btnMbCancle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		memberControllerInit();
		
		btnMbConfirm.setOnAction(event->handleBtnConfirm());
		btnMbCancle.setOnAction(event->handleBtnCancel());
		btnMbIdConfirm.setOnAction(event->handleBtnIdConfirm());
	}

	private void handleBtnIdConfirm() {
		
		MemberVO idOverlap = null;
		MemberDAO memberDB = new MemberDAO();
		
		idOverlap = new MemberVO(txtMbId.getText().trim());
		memberDB.idOverlap(idOverlap);
		
	}

	private void handleBtnCancel() {
		Stage stage = (Stage) btnMbCancle.getScene().getWindow();
		stage.close();		
	}

	private void handleBtnConfirm() {
		MemberVO joinMember = null;
		MemberDAO memberDB = new MemberDAO();
		
		if (txtMbPw1.getText().equals(txtMbPw2.getText())==false) {  //이름과 반이 안적혀져있으면 경고창을 출력
			
			txtMbPw1.clear();
			txtMbPw2.clear();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("패스워드 입력 오류");
			alert.setHeaderText("패스워드를 올바르게 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}else{
			if (txtMbName.getText().equals("") || txtMbId.getText().equals("") || txtMbPw1.getText().equals("") ||
					txtMbPw2.getText().equals("") || comboMbPhone1.getValue().toString().equals("")||
					txtMbPhone2.getText().equals("") || txtMbPhone3.getText().equals("") ||
					comboMbBankName.getItems().equals("") || txtMbAccount.getText().equals("") ||
					txtMbAccountHolder.getText().equals("")) {  
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("정보 입력");
				alert.setHeaderText("입력란을 모두 채우세요.");
				alert.setContentText("다음에는 주의하세요!");
			
				alert.showAndWait();
			}else{
				joinMember = new MemberVO(txtMbId.getText().trim(),
						txtMbName.getText().trim(),
						comboMbPhone1.getSelectionModel().getSelectedItem().toString().trim(),
						comboMbBankName.getSelectionModel().getSelectedItem().toString().trim(),			
						txtMbAccount.getText().trim(),
						txtMbAccountHolder.getText().trim(),
						txtMbPw1.getText().trim(),
						txtMbPw2.getText().trim(),
						txtMbPhone2.getText().trim(),
						txtMbPhone3.getText().trim());
				memberDB.joinMember(joinMember);	
				if(memberDB != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("등록 성공");
					alert.setContentText("등록 성공하였습니다.");
					alert.showAndWait();
					Stage stage = (Stage) btnMbCancle.getScene().getWindow();
					stage.close();
					}
				try {
					Stage mainStage = new Stage();
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
					Scene scene = new Scene(root);					
					mainStage.setTitle("TOTO JoinMember");
					mainStage.setScene(scene);
					mainStage.show();
					Stage window = (Stage)( btnMbConfirm.getScene().getWindow());
					window.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	public void memberControllerInit() {
		comboMbBankName.setEditable(false);
		comboMbPhone1.setEditable(false);
		comboMbPhone1.setValue("02");
		
		ObservableList<String> comboMbPhoneList = FXCollections.observableArrayList();
		comboMbPhoneList.add("02");
		comboMbPhoneList.add("031");
		comboMbPhoneList.add("032");
		comboMbPhoneList.add("010");
		comboMbPhoneList.add("011");
		comboMbPhoneList.add("017");
		comboMbPhone1.setItems(comboMbPhoneList);
		
		ObservableList<String> comboMbBankNameList = FXCollections.observableArrayList();
		comboMbBankNameList.add("국민은행");
		comboMbBankNameList.add("신한은행");
		comboMbBankNameList.add("기업은행");
		comboMbBankNameList.add("우리은행");
		comboMbBankNameList.add("외환은행");
		comboMbBankNameList.add("농협은행");
		comboMbBankNameList.add("sc제일은행");
		comboMbBankName.setItems(comboMbBankNameList);
		
		DecimalFormat format = new DecimalFormat("####");
		txtMbPhone2.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==5) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		
		txtMbPhone3.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==5) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		
		DecimalFormat format1 = new DecimalFormat("################");
		txtMbAccount.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format1.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==17) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
	}
}