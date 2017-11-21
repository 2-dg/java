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

public class RegUserController implements Initializable{

	@FXML private TextField txtRUid;
	@FXML private TextField txtRUname;
	@FXML private ComboBox comboRUphoneNum1;
	@FXML private TextField txtRUphoneNum2;
	@FXML private TextField txtRUphoneNum3;
	@FXML private TextField txtRUpw1;
	@FXML private TextField txtRUpw2;
	@FXML private ComboBox comboRUbankName;
	@FXML private TextField txtRUaccountNum;
	@FXML private TextField txtRUaccountHolder;
	@FXML private ComboBox comboRUstatus;
	@FXML private TextField txtRUmoney;
	
	@FXML private Button btnRUreg;
	@FXML private Button btnRUcancel;
	@FXML private Button btnRUIdConfirm;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		regUserInit();
		
		btnRUreg.setOnAction(event->handleBtnUserReg());
		
		btnRUcancel.setOnAction(event->handleBtnUserCancel());
		
		btnRUIdConfirm.setOnAction(event->handleBtnUserIdConfirm());
		
	}
	
	protected void handleBtnUserIdConfirm() {
		MemberVO idOverlap = null;
		MemberDAO memberDAO = new MemberDAO();
		idOverlap = new MemberVO(txtRUid.getText().trim());
		memberDAO.idOverlap(idOverlap);	
	}

	private void handleBtnUserCancel() {
		Stage stage = (Stage) btnRUcancel.getScene().getWindow();
		stage.close();		
	}

	protected void handleBtnUserReg() {
		MemberVO memberVO=null;
		MemberDAO memberDAO=new MemberDAO();
		if(txtRUid.getText().isEmpty() || txtRUname.getText().isEmpty() || comboRUphoneNum1.getItems().equals("") ||
				txtRUphoneNum2.getText().isEmpty() || txtRUphoneNum3.getText().isEmpty() || comboRUbankName.getItems().equals("") ||
				txtRUaccountNum.getText().isEmpty() || txtRUaccountHolder.getText().isEmpty() || comboRUstatus.getItems().equals("") ||
				txtRUmoney.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���");
			alert.setHeaderText("�߸��� �Է�");
			alert.setContentText("���������� ����� �Է��ϼ���.");
			alert.showAndWait();
		}else{
			if(txtRUpw1.getText().equals(txtRUpw2.getText())==false){
				txtRUpw1.clear();
				txtRUpw2.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setHeaderText("��й�ȣ ����.");
				alert.setContentText("��й�ȣ�� ����� �Է����ֽÿ�.");
				alert.showAndWait();
			}else{
				try{
				memberVO=new MemberVO(txtRUid.getText().trim(), txtRUname.getText().trim(),
					txtRUpw1.getText().trim(), txtRUpw2.getText().trim(),
					comboRUphoneNum1.getSelectionModel().getSelectedItem().toString(),
					txtRUphoneNum2.getText().trim(),
					txtRUphoneNum3.getText().trim(),
					comboRUbankName.getSelectionModel().getSelectedItem().toString(),
					txtRUaccountNum.getText().trim(), txtRUaccountHolder.getText().trim(),
					txtRUmoney.getText().trim(),
					comboRUstatus.getSelectionModel().getSelectedItem().toString());
				memberDAO.joinMember(memberVO);
				}catch(java.lang.NullPointerException e) {
					memberDAO = null;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("���");
					alert.setContentText("ȸ������ ���Է�");
					alert.setHeaderText("ȸ�������� ����� �Էµ��� �ʾҽ��ϴ�.");
					alert.showAndWait();					
					}
				if (memberDAO != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�߰�");
					alert.setHeaderText("ȸ�� �߰� �Ϸ�.");
					alert.setContentText("ȸ�� �߰��� �Ϸ��Ͽ����ϴ�.");
					alert.showAndWait();
					ViewController.listOfMembers.clear();
					ViewController.totalListMM();
					Stage stage = (Stage) btnRUcancel.getScene().getWindow();
					stage.close();
				}
			}
		}
	}

	public void regUserInit(){
		
		comboRUbankName.setEditable(false);
		
		if(comboRUphoneNum1.getSelectionModel().isEmpty()) {
			comboRUphoneNum1.setValue("010");
		}
		if(comboRUstatus.getSelectionModel().isEmpty()) {
			comboRUstatus.setValue("�Ϲ�ȸ��");
		}
		
		ObservableList<String> phoneNumList = FXCollections.observableArrayList();
		phoneNumList.add("02");
		phoneNumList.add("031");
		phoneNumList.add("032");
		phoneNumList.add("010");
		phoneNumList.add("011");
		phoneNumList.add("017");
		comboRUphoneNum1.setItems(phoneNumList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add("������");
		statusList.add("�Ϲ�ȸ��");
		statusList.add("������Ʈ");
		comboRUstatus.setItems(statusList);
		
		ObservableList<String> bankNameList = FXCollections.observableArrayList();
		bankNameList.add("��������");
		bankNameList.add("��������");
		bankNameList.add("�������");
		bankNameList.add("�츮����");
		bankNameList.add("��ȯ����");
		bankNameList.add("��������");
		bankNameList.add("sc��������");
		comboRUbankName.setItems(bankNameList);
		
		DecimalFormat four = new DecimalFormat("####");
		DecimalFormat sixteen = new DecimalFormat("################");
		DecimalFormat ten = new DecimalFormat("##########");

		txtRUphoneNum2.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = four.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event;
			}
		}));
		txtRUphoneNum3.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = four.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event;
			}
		}));
		txtRUaccountNum.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = sixteen.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 17) {
				return null;
			} else {
				return event;
			}
		}));		
		txtRUmoney.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = ten.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 11) {
				return null;
			} else {
				return event;
			}
		}));
	}	
}