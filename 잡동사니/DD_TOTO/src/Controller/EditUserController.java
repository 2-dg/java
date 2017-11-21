package Controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import Model.MemberVO;
import dao.MemberDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class EditUserController implements Initializable{
	@FXML private TextField txtEUid;
	@FXML private TextField txtEUname;
	@FXML private ComboBox comboEUphoneNum1;
	@FXML private TextField txtEUphoneNum2;
	@FXML private TextField txtEUphoneNum3;
	@FXML private TextField txtEUbankName;
	@FXML private TextField txtEUaccountNum;
	@FXML private TextField txtEUaccountHolder;
	@FXML private ComboBox comboEUstatus;
	@FXML private TextField txtEUmoney;
	
	@FXML private Button btnEUedit;
	@FXML private Button btnEUcancle;

	private MemberVO memberVO = ViewController.getselectedMember();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//수정창 설정 초기화
		editMemberInit();
		//수정버튼 이벤트
		btnEUedit.setOnAction(event->handleBtnEdit());
		//취소버튼 이벤트
		btnEUcancle.setOnAction(event->{
		Stage stage = (Stage) btnEUcancle.getScene().getWindow();stage.close();}); 
	}

	private void handleBtnEdit() {
		Stage stage = (Stage) btnEUedit.getScene().getWindow();
		MemberVO memberEdit = null;
		MemberDAO memberDAO = null;
		if (txtEUid.getText().isEmpty() || txtEUname.getText().isEmpty() || comboEUphoneNum1.getItems().equals("")
				|| txtEUphoneNum2.getText().isEmpty() || txtEUphoneNum3.getText().isEmpty()
				|| txtEUbankName.getText().isEmpty() || txtEUaccountNum.getText().isEmpty()
				|| txtEUaccountHolder.getText().isEmpty() || comboEUstatus.getItems().equals("")
				|| txtEUmoney.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("경고");
			alert.setHeaderText("잘못된 입력");
			alert.setContentText("유저정보를 제대로 입력하세요.");
			alert.showAndWait();
		} else {
            String pn1 = comboEUphoneNum1.getValue().toString();
            String pn2 = txtEUphoneNum2.getText();
            String pn3 = txtEUphoneNum3.getText();
            String pn = pn1+"-"+pn2+"-"+pn3;
            
			memberEdit = new MemberVO( txtEUname.getText(), txtEUid.getText(),
					comboEUphoneNum1.getValue().toString(),
					txtEUphoneNum2.getText(), txtEUphoneNum3.getText(), txtEUbankName.getText(),
					txtEUaccountNum.getText(), txtEUaccountHolder.getText(), txtEUmoney.getText(),
					comboEUstatus.getValue().toString(), pn);
			memberEdit.setId(memberVO.getId());
			stage.close();
			memberDAO = new MemberDAO();
			memberDAO.memberEdit(memberEdit, memberVO.getId());
			ViewController.listOfMembers.removeAll(ViewController.listOfMembers);
			ViewController.totalListMM();
		}	      
	}

	public void editMemberInit() {
        
		txtEUid.setEditable(false);
		txtEUname.setEditable(true);
		comboEUphoneNum1.setEditable(false);
		txtEUphoneNum2.setEditable(true);
		txtEUphoneNum3.setEditable(true);
		txtEUbankName.setEditable(true);
		txtEUaccountNum.setEditable(true);
		txtEUaccountHolder.setEditable(true);
		comboEUstatus.setEditable(false);
		txtEUmoney.setEditable(true);
		
		try {
			txtEUid.setText(memberVO.getId());
			txtEUname.setText(memberVO.getName());
			comboEUphoneNum1.setValue(memberVO.getPhoneNum1());
			txtEUphoneNum2.setText(memberVO.getPhoneNum2());
			txtEUphoneNum3.setText(memberVO.getPhoneNum3());
			txtEUbankName.setText(memberVO.getBankName());
			txtEUaccountNum.setText(memberVO.getAccountNum());
			txtEUaccountHolder.setText(memberVO.getAccountHolder());
			comboEUstatus.setValue(memberVO.getUserType());
			txtEUmoney.setText(memberVO.getUserMoney());
		} catch (Exception e) {
		}
		
		ObservableList<String> phoneNumList = FXCollections.observableArrayList();
		phoneNumList.add("02");
		phoneNumList.add("031");
		phoneNumList.add("032");
		phoneNumList.add("010");
		phoneNumList.add("011");
		phoneNumList.add("017");
		comboEUphoneNum1.setItems(phoneNumList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add("관리자");
		statusList.add("일반회원");
		statusList.add("블랙리스트");
		comboEUstatus.setItems(statusList);
		
		DecimalFormat tenFormat = new DecimalFormat("##########");
		DecimalFormat fourFormat = new DecimalFormat("####");
		DecimalFormat moneyFormat = new DecimalFormat("###########");
		DecimalFormat accountNumFormat = new DecimalFormat("################");
		
		txtEUid.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = tenFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==11) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtEUphoneNum2.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = fourFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==5) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtEUphoneNum3.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = fourFormat.parse(event.getControlNewText(),parsePosition);
			if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
					||event.getControlNewText().length()==5) {
				return null;
			}else {
				return event;
			}}));
		txtEUaccountNum.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = accountNumFormat.parse(event.getControlNewText(),parsePosition);
			 if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
	    			  ||event.getControlNewText().length()==17) {
	    		  return null;
	    	  }else {
	    		  return event;
	    	  }}));
		txtEUmoney.setTextFormatter(new TextFormatter<>(event -> {
			if(event.getControlNewText().isEmpty()) {
				return event;				
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = tenFormat.parse(event.getControlNewText(),parsePosition);
			if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
					||event.getControlNewText().length()==11) {
				return null;
			}else {
				return event;
			}}));
	}
}