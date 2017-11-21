package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Student;

public class RootController implements Initializable{
	@FXML private TableView<Student> tableView = new TableView<>();
	@FXML private TextField txtName;
	@FXML private ComboBox<String> cbYear;
	@FXML private TextField txtBan;
	@FXML private ToggleGroup genderGroup;
	@FXML private RadioButton rbMale;
	@FXML private RadioButton rbFemale;
	@FXML private Button btnTotal;
	@FXML private Button btnAvg;
	@FXML private Button btnInit;
	@FXML private Button btnOk;
	@FXML private Button btnExit;
	@FXML private Button btnEdit;
	@FXML private Button btnDelete;
	@FXML private TextField txtKo;
	@FXML private TextField txtEng;
	@FXML private TextField txtMath;
	@FXML private TextField txtSic;
	@FXML private TextField txtSoc;
	@FXML private TextField txtMusic;
	@FXML private TextField txtTotal;
	@FXML private TextField txtAvg;
	@FXML private TextField txtSearch;
	@FXML private Button btnSearch;
	private Student student = new Student();
	private ObservableList<Student> data = FXCollections.observableArrayList();
	private ObservableList<Student> selectStudent;
	private boolean editDelete = true;
	private int selectedIndex=0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�ش��ϴ� �ʵ忡 �Է��� �� �ǰ� ���� ��
		txtTotal.setEditable(false);
		txtAvg.setEditable(false);

		//��ư ������ ���Ƶ�
		btnAvg.setDisable(true);
		btnOk.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		
		//�г� ����Ʈ �信 �׸� �߰�
		cbYear.setItems(FXCollections.observableArrayList("1","2","3","4","5","6"));
		
		//���̺�信�� �� �Ӽ��� ����
		TableColumn colName = new TableColumn("����");
		colName.setMaxWidth(70);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn colLevel = new TableColumn("�г�");
		colLevel.setMaxWidth(39);
		colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
		TableColumn colBan = new TableColumn("��");
		colBan.setMaxWidth(39);
		colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));
		TableColumn colGender = new TableColumn("����");
		colGender.setMaxWidth(39);
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		TableColumn colKorean = new TableColumn("����");
		colKorean.setMaxWidth(39);
		colKorean.setCellValueFactory(new PropertyValueFactory<>("korean"));
		TableColumn colEnglish = new TableColumn("����");
		colEnglish.setMaxWidth(39);
		colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
		TableColumn colMath = new TableColumn("����");
		colMath.setMaxWidth(39);
		colMath.setCellValueFactory(new PropertyValueFactory<>("Math"));
		TableColumn colSic = new TableColumn("����");
		colSic.setMaxWidth(39);
		colSic.setCellValueFactory(new PropertyValueFactory<>("sic"));
		TableColumn colSoc = new TableColumn("��ȸ");
		colSoc.setMaxWidth(39);
		colSoc.setCellValueFactory(new PropertyValueFactory<>("soc"));
		TableColumn colMusic = new TableColumn("����");
		colMusic.setMaxWidth(39);
		colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));
		TableColumn colTotal = new TableColumn("����");
		colTotal.setMaxWidth(39);
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		TableColumn colAvg = new TableColumn("���");
		colAvg.setMaxWidth(39);
		colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
		
		//���̺�信 �� �Ӽ��� �߰�
		tableView.setItems(data);
		tableView.getColumns().addAll(colName,colLevel,colBan,colGender,colKorean,colEnglish,colMath,
				colSic,colSoc,colMusic,colTotal,colAvg);
		
		btnTotal.setOnAction((ActionEvent event)-> {
			try {
				handlerBtnTotalAction(event);
			}catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� �Է�");
				alert.setHeaderText("������ �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}});
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(btnEdit.getOnAction().equals(false)) {
					System.out.println("������ư ����");
				}
				if(txtName.getText().equals("")||txtBan.getText().equals("")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("���� �Է�");
					alert.setHeaderText("�л��� �̸��� ���� �Է½ÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}try {
				data.add(new Student(txtName.getText(),cbYear.getSelectionModel().getSelectedItem(),
						txtBan.getText(),genderGroup.getSelectedToggle().getUserData().toString(),
						txtKo.getText(),txtEng.getText(),txtMath.getText(),txtSic.getText(),
						txtSoc.getText(),txtMusic.getText(), txtTotal.getText(), txtAvg.getText()));
				}catch(Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� �Է�");
				alert.setHeaderText("�л��� ���� �Է�");
				alert.setContentText("�������� ����!");
				alert.showAndWait();
				}
				txtName.setEditable(true);
				cbYear.getSelectionModel().clearSelection();
				txtBan.setEditable(true);
				txtKo.setEditable(true);
				txtEng.setEditable(true);
				txtMath.setEditable(true);
				txtSic.setEditable(true);
				txtSoc.setEditable(true);
				txtMusic.setEditable(true);
				handlerBtnInitAction(event);}
			});
		btnAvg.setOnAction(event -> handlerBtnAvgAction(event));
		btnInit.setOnAction(event -> handlerBtnInitAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
		
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectStudent = tableView.getSelectionModel().getSelectedItems();
				selectedIndex = tableView.getSelectionModel().getSelectedIndex();
				//System.out.println("�� ��ȣ : "+selectedIndex);
				try {
				txtName.setText(selectStudent.get(0).getName());
				cbYear.setValue(selectStudent.get(0).getLevel());
				if(selectStudent.get(0).getGender().equals("����")) {
					rbMale.setSelected(true);
					rbFemale.setDisable(true);
				}else {
					rbFemale.setSelected(true);
					rbMale.setDisable(true);
				}
				txtBan.setText(selectStudent.get(0).getBan());
				txtKo.setText(selectStudent.get(0).getKorean());
				txtEng.setText(selectStudent.get(0).getEnglish());
				txtMath.setText(selectStudent.get(0).getMath());
				txtSic.setText(selectStudent.get(0).getMath());
				txtSoc.setText(selectStudent.get(0).getMath());
				txtMusic.setText(selectStudent.get(0).getMath());
				txtTotal.setText(selectStudent.get(0).getMath());
				txtAvg.setText(selectStudent.get(0).getMath());
				
				txtName.setEditable(false);
				txtBan.setEditable(false);
				cbYear.setDisable(true);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				editDelete = false;
				}catch(java.lang.NullPointerException e) {
				}
					
				
			}
		});
		
		
		
		
		
		
		
	}//end of initialize
	
	
	private void handlerBtnDeleteAction(ActionEvent event) {
		data.remove(selectedIndex);
		txtName.setEditable(true);
		txtBan.setEditable(true);
		txtKo.setEditable(true);
		txtEng.setEditable(true);
		txtMath.setEditable(true);
		txtSic.setEditable(true);
		txtSoc.setEditable(true);
		txtMusic.setEditable(true);
		cbYear.getSelectionModel().clearSelection();
		
		handlerBtnInitAction(event);
		editDelete = true;
	}
	private void handlerBtnEditAction(ActionEvent event) {
		data.remove(selectedIndex);
		data.add(selectedIndex, new Student(txtName.getText(),cbYear.getSelectionModel().getSelectedItem(),
						txtBan.getText(),genderGroup.getSelectedToggle().getUserData().toString(),
						txtKo.getText(),txtEng.getText(),txtMath.getText(),txtSic.getText(),
						txtSoc.getText(),txtMusic.getText(), txtTotal.getText(), txtAvg.getText()));
		txtName.setEditable(true);
		txtBan.setEditable(true);
		txtKo.setEditable(true);
		txtEng.setEditable(true);
		txtMath.setEditable(true);
		txtSic.setEditable(true);
		txtSoc.setEditable(true);
		txtMusic.setEditable(true);
		cbYear.getSelectionModel().clearSelection();
		
		handlerBtnInitAction(event);
		
	}


	private Object handlerBtnSearchAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}


	protected void handlerBtnInitAction(ActionEvent event) {
		txtName.clear();
		txtName.setEditable(true);
		cbYear.setDisable(false);
		cbYear.getSelectionModel().clearSelection();
		rbFemale.setDisable(false);
		rbMale.setDisable(false);
		genderGroup.selectToggle(null);
		txtBan.clear();
		txtBan.setEditable(true);
		txtKo.clear();
		txtEng.clear();
		txtMath.clear();
		txtSic.clear();
		txtSoc.clear();
		txtMusic.clear();
		txtTotal.clear();
		txtAvg.clear();
		btnTotal.setDisable(false);
		btnOk.setDisable(true);
		
	}
	protected void handlerBtnTotalAction(ActionEvent event) {
		int korean = Integer.parseInt(txtKo.getText());
		int english = Integer.parseInt(txtEng.getText());
		int math = Integer.parseInt(txtMath.getText());
		int sic = Integer.parseInt(txtSic.getText());
		int soc = Integer.parseInt(txtSoc.getText());
		int music = Integer.parseInt(txtMusic.getText());
		int total = korean+english+math+sic+soc+music;
		
		student.setName(txtName.getText());
		student.setLevel(cbYear.getItems()+"");
		student.setBan(txtBan.getText());
		student.setGender(genderGroup.getUserData()+"");
		student.setKorean(txtKo.getText());
		student.setEnglish(txtEng.getText());
		student.setMath(txtMath.getText());
		student.setSic(txtSic.getText());
		student.setSoc(txtSoc.getText());
		student.setMusic(txtMusic.getText());
		
		student.setTotal(total+"");
		txtTotal.setText(student.getTotal());
		btnAvg.setDisable(false);
		btnTotal.setDisable(true);
		
	}
	public void handlerBtnAvgAction(ActionEvent e) {
		student.setAvg((Integer.parseInt(student.getTotal())/6.0)+"");
		txtAvg.setText(student.getAvg()+"");
		txtName.setEditable(false);
		cbYear.setEditable(false);
		txtBan.setEditable(false);
		txtKo.setEditable(false);
		txtEng.setEditable(false);
		txtMath.setEditable(false);
		txtSic.setEditable(false);
		txtSoc.setEditable(false);
		txtMusic.setEditable(false);
		
		btnAvg.setDisable(true);
		btnOk.setDisable(false);
		
		if(editDelete == false) {
			btnOk.setDisable(true);
		}
	}
}