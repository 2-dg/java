package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.StudentVO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML
	private TableView<StudentVO> tableView = new TableView<>();
	@FXML
	private TextField txtName;
	@FXML
	private ComboBox<String> cbYear;
	@FXML
	private TextField txtBan;
	@FXML
	private ToggleGroup genderGroup;
	@FXML
	private RadioButton rbMale;
	@FXML
	private RadioButton rbFemale;
	@FXML
	private Button btnSum;
	@FXML
	private Button btnAvg;
	@FXML
	private Button btnInit;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private TextField txtKo;
	@FXML
	private TextField txtEng;
	@FXML
	private TextField txtMath;
	@FXML
	private TextField txtSic;
	@FXML
	private TextField txtSoc;
	@FXML
	private TextField txtMusic;
	@FXML
	private TextField txtTotal;
	@FXML
	private TextField txtAvg;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnSearch;
	
	@FXML
	private Button btnTotalList;
	@FXML
	private Button btnBarChart;
	@FXML
	private DatePicker dpDate;
	@FXML
	private HBox imageBox;
	@FXML
	private Button btnImageFile;
	@FXML
	private ImageView imageView;

	ObservableList<StudentVO> data = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null; // ���̺��� ������ ���� ����
	StudentVO student = new StudentVO(); // ���� ��� ���

	boolean editDelete = true; // Ȯ�� ��ư ���� ����
	int selectedIndex; // ���̺��� ������ �л� ���� �ε��� ����

	private Stage primaryStage;
	String selectFileName = ""; // �̹��� ���ϸ�
	String localUrl = ""; // �̹��� ���� ���
	Image localImage;

	int no; // ������ ���̺��� ������ �л��� ��ȣ ����
	File selectedFile = null;

	// �̹��� ó�� ( �̹��� ������ ������ �Ű������� ���� ��ü ����)-
	// 
	private File dirSave = new File("C:/images");
	// �̹��� �ҷ��� ������ ������ ���� ��ü ����
	private File file = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		txtTotal.setEditable(false);
		txtAvg.setEditable(false);
		btnAvg.setDisable(true);
		btnOk.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		btnImageFile.setDisable(true);
		dpDate.setValue(LocalDate.now());

		// �г� �޺� �� ����
		cbYear.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));

		// ���� �����ʵ忡 ���ڸ� �Է�
		setTextFormatterFunction();

		//���̺�信  StudentVO ��ü�� ������.
		setTableColumnValueFactoryFunction();

		// �л� ��ü ����  (���ڵ忡 �ִ� �л������� �����ͼ�  ArrayList<StudentVO> list�� StudentVO�� ��ȯ�ؼ� data�� �־��ִ� �Լ�)
		totalList();
		
		//���̺�信 ObservableList<StudentVO> data���� ������.
		tableView.setItems(data);

		// �⺻ �̹���
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);//backgroundLoading => false
		imageView.setImage(localImage);
		
		btnTotalList.setOnAction(event -> handlerBtnTotalListActoion(event)); // ��ü��ư����Ʈ ����������� �ٽø���
		btnSum.setOnAction(event -> handlerBtnSumAction(event));//������ ���ϴ� ����Ʈ
		btnOk.setOnAction(event -> handlerBtnOkAction(event));//�л� ������ ����
		txtName.setOnMouseClicked(event -> handlerTxtNameActoion(event)); //�̸��� Ŭ���ϸ� �ʱ�ȭ
		btnAvg.setOnAction(event -> handlerBtnAvgActoion(event)); // ��� ���
		btnInit.setOnAction(event -> handlerBtnInitActoion(event)); // �ʱ�ȭ
		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // ����
		btnSearch.setOnAction(event -> handlerBtnSearchActoion(event)); // �л��̸�����  �˻�																	
		btnEdit.setOnAction(event -> handlerBtnEditActoion(event)); // �л� ���� ����
		btnDelete.setOnAction(event -> handlerBtnDeleteActoion(event)); // �л� ��������
		btnBarChart.setOnAction(event -> handlerBtnBarChartActoion(event)); // ����Ʈ
		tableView.setOnMouseClicked(event -> handlerBtnPieChartActoion(event)); // ���̺��� �л� ����
		btnImageFile.setOnAction(event -> handlerBtnImageFileActoion(event)); // �̹��� ����â
																				
	}
	

	public void setTableColumnValueFactoryFunction(){
		tableView.setEditable(false);

		// ���̺� �� �÷��̸� ����
		TableColumn colNo = new TableColumn("NO.");
		colNo.setMaxWidth(40);
		colNo.setStyle("-fx-allignment: CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn colName = new TableColumn("����");
		colName.setMaxWidth(60);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colLevel = new TableColumn("�г�");
		colLevel.setMaxWidth(40);
		colLevel.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn colBan = new TableColumn("��");
		colBan.setMaxWidth(40);
		colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));

		TableColumn colGender = new TableColumn("����");
		colGender.setMaxWidth(40);
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn colKorean = new TableColumn("����");
		colKorean.setMaxWidth(40);
		colKorean.setCellValueFactory(new PropertyValueFactory<>("korean"));

		TableColumn colEnglish = new TableColumn("����");
		colEnglish.setMaxWidth(40);
		colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));

		TableColumn colMath = new TableColumn("����");
		colMath.setMaxWidth(40);
		colMath.setCellValueFactory(new PropertyValueFactory<>("math"));

		TableColumn colSic = new TableColumn("����");
		colSic.setMaxWidth(40);
		colSic.setCellValueFactory(new PropertyValueFactory<>("sic"));

		TableColumn colSoc = new TableColumn("��ȸ");
		colSoc.setMaxWidth(40);
		colSoc.setCellValueFactory(new PropertyValueFactory<>("soc"));

		TableColumn colMusic = new TableColumn("����");
		colMusic.setMaxWidth(40);
		colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));

		TableColumn colTotal = new TableColumn("����");
		colTotal.setMaxWidth(40);
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		TableColumn colAvg = new TableColumn("���");
		colAvg.setMaxWidth(50);
		colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));

		TableColumn colRegister = new TableColumn("�����");
		colRegister.setMinWidth(90);
		colRegister.setCellValueFactory(new PropertyValueFactory<>("register"));

		TableColumn colImageFileName = new TableColumn("�̹���");
		colImageFileName.setMinWidth(260);
		colImageFileName.setCellValueFactory(new PropertyValueFactory<>("filename"));

		tableView.getColumns().addAll(colNo, colName, colLevel, colBan, colGender, colKorean, colEnglish, colMath,
				colSic, colSoc, colMusic, colTotal, colAvg, colRegister, colImageFileName);
		
		
	}
	
	
	public void setTextFormatterFunction(){
		DecimalFormat format = new DecimalFormat( "###" );//3�ڸ�����
		txtKo.setTextFormatter( new TextFormatter<>(event ->
		{
		    if ( event.getControlNewText().isEmpty() )//�Է��� ���� ������
		    {
		        return event;
		    }

		    ParsePosition parsePosition = new ParsePosition( 0 );//��ġ����
			
		//���ڿ��� �Է��Ѱ��� "###" 3�ڸ� ������������ ��ȯ��. ��)a12 -> 3�ڸ� ������������ �ٲܼ� ������ null��
		    Object object = format.parse( event.getControlNewText(), parsePosition );
			
		    //��ȯ�Ȱ��� null(���ھƴ� ���ڳ� Ư�����ڸ� Ŭ���ϸ� null�� �����ؼ� �ش�� �ؽ�Ʈ�� �ش繮�ڸ� ����������)
		    // �̰ų� ��ȯ����ġ�� ���̰� �������̺��� ���ų�, 
		    //���� �Է��� ���̰� 4�̸� null �ƴϸ� �ش�Ȱ����� ������.	
		    if ( object == null || parsePosition.getIndex() < event.getControlNewText().length() || event.getControlNewText().length()==4 )
		    {
		        return null;
		    }
		    else
		    {
		        return event;
		    }
		}));
		
		txtEng.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		
		txtMath.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		
		txtSic.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		
		txtSoc.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		
		txtMusic.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		
	}
	
	// ���� â
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/***********************************************************
	 * imageDelete() �̹��� ���� �޼ҵ�.
	 * 
	 * @param (������
	 *            ���ϸ�)
	 * @return �������θ� ����
	 ***********************************************************/
	public boolean imageDelete(String fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + fileName); // ���� �̹��� ����  
																						
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();

				// �⺻ �̹���
				localUrl = "/image/default.png";
				localImage = new Image(localUrl, false);
				imageView.setImage(localImage);
			}
		} catch (Exception ie) {
			System.out.println("ie = [ " + ie.getMessage() + "]");
			result = false;
		}
		return result;
	}

	/***********************************************************
	 * imageSave() �̹��� ���� �޼ҵ�.
	 * 
	 * @param (�о��
	 *            ���� ��ü)
	 ***********************************************************/
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// �̹��� ���ϸ� ����
			fileName = "student" + System.currentTimeMillis() + "_" + file.getName();

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));
			//System.out.println(dirSave.getAbsolutePath() + "\\" + fileName);
			
			// ������ �̹��� ���� InputStream�� �������� �̸����� ���� -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return fileName;
	}

	// �л� ��ü ����Ʈ (���ڵ忡 �ִ� �л������� �����ͼ�  ArrayList<StudentVO> list�� StudentVO�� ��ȯ�ؼ� data�� �־��ִ� �Լ�)
	public void totalList() {
		Object[][] totalData;

		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = new StudentVO();
		ArrayList<String> title;
		ArrayList<StudentVO> list;

		title = sDao.getColumnName();
		for(String str : title){
			System.out.println("totallist= "+str);
		}
		
		int columnCount = title.size();

		list = sDao.getStudentTotal();

		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {

			sVo = list.get(index);

			data.add(sVo);
		}
	}

	// �ʱ�ȭ �޼ҵ�
	public void init(){
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
		
		btnSum.setDisable(false);
		btnOk.setDisable(true);
		btnDelete.setDisable(true);
		btnEdit.setDisable(true);

		// �⺻ �̹���
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);
	}

	// ����
	private void handlerBtnOkAction(ActionEvent event) {
			/*if (btnEdit.getOnAction().equals(false)) {
			System.out.println("������ư ����");
		}*/
		if (txtName.getText().equals("") || txtBan.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText("�л��� �̸��� ���� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
	
			alert.showAndWait();
		}
	
		data.removeAll(data);		
		System.out.println("btnOk���� ������ ="+data.size());// 0��  �����
	
		StudentVO sVo = null;
		StudentDAO sDao = new StudentDAO();//����Ÿ���̽�  ó���ϴ� ��ü����
	
		// ���� ���� File dirSave =new File("C:/images");
		try {
			File dirMake = new File(dirSave.getAbsolutePath());
			// �̹��� ���� ���� ������ ����
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
		} catch (Exception ie) {
			System.out.println("ie = [ " + ie.getMessage() + "]");
		}
	
		// �̹��� ���� ����
		String fileName = imageSave(selectedFile);//�̹��� ���Ϲ�ư Ŭ���� ���Ͽ� �ش�� ���� selectedFile�� ����Ǿ�����. 
	
		// �л� ���� ����
		if (event.getSource().equals(btnOk)) {
			try {
				sVo = new StudentVO(txtName.getText(), cbYear.getSelectionModel().getSelectedItem(),
						txtBan.getText(), genderGroup.getSelectedToggle().getUserData().toString(),
						Integer.parseInt(txtKo.getText().trim()), Integer.parseInt(txtEng.getText().trim()),
						Integer.parseInt(txtMath.getText().trim()), Integer.parseInt(txtSic.getText().trim()),
						Integer.parseInt(txtSoc.getText().trim()), Integer.parseInt(txtMusic.getText().trim()),
						Integer.parseInt(txtTotal.getText().trim()), Double.parseDouble(txtAvg.getText().trim()),
						fileName);
				sDao = new StudentDAO();
				sDao.getStudentregiste(sVo);//���̺� ���Խ���(insert ����)
	
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� �Է�");
				alert.setHeaderText("�л��� ������ �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
	
				alert.showAndWait();
			}
			if (sDao != null) {
	
				totalList();//�л����� ���̺��� �����ͼ� data�� ��� ����
	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� ���� �Է�");
				alert.setHeaderText(txtName.getText() + " �л��� ������ ���������� �߰��Ǿ����ϴ�..");
				alert.setContentText("���� �л��� ������ �Է��ϼ���");
				alert.showAndWait();
	
				btnImageFile.setDisable(true);//��ư�̹������� ��Ȱ��ȭ
	
				// �⺻ �̹���
				localUrl = "/image/default.png";
				localImage = new Image(localUrl, false);
				imageView.setImage(localImage);
	
	
				txtName.setEditable(true);
				cbYear.getSelectionModel().clearSelection();
				txtBan.setEditable(true);
				txtKo.setEditable(true);
				txtEng.setEditable(true);
				txtMath.setEditable(true);
				txtSic.setEditable(true);
				txtSoc.setEditable(true);
				txtMusic.setEditable(true);
				
				handlerBtnInitActoion(event);//ȭ�� �ʱ�ȭó����.
			}
		}

	}

	//
	protected void handlerBtnTotalListActoion(ActionEvent event) {
		try {
			data.removeAll(data);
			totalList();// �л� ��ü ����
		} catch (Exception e) {	}
		
	}

	// �̹��� ���� ���� â
	public void handlerBtnImageFileActoion(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// �̹��� ���� ���
				// URL�� �� �ּ�.
		        // URI�� ��Ʈ��ũ���� ����ũ�� ���𰡸� �����ϱ� ���ؼ�, �������α׷����� ���� ����
				// file:///C:/rafaelnadal/tournaments/2009/BNP.txt
				localUrl = selectedFile.toURI().toURL().toString();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		localImage = new Image(localUrl, false);

		imageView.setImage(localImage);
		imageView.setFitHeight(250);
		imageView.setFitWidth(230);

		btnOk.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}

	// �л� ����
	public void handlerBtnDeleteActoion(ActionEvent event) {

		StudentDAO sDao = null;
		sDao = new StudentDAO();

		try {
			sDao.getStudentDelete(no);
			data.removeAll(data);
			// �л� ��ü ����
			totalList();
			// �̹��� ���� ����
			imageDelete(selectFileName);

			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editDelete = true;
	}

	// ���� ����
	public void handlerBtnEditActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/View/formedit.fxml"));
			
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnOk.getScene().getWindow());
			dialog.setTitle("����");

			Parent parentEdit = (Parent) loader.load();
			StudentVO studentEdit = tableView.getSelectionModel().getSelectedItem();
			selectedIndex = tableView.getSelectionModel().getSelectedIndex();

			TextField editNo = (TextField) parentEdit.lookup("#txtNo");
			TextField editName = (TextField) parentEdit.lookup("#txtName");
			TextField editYear = (TextField) parentEdit.lookup("#txtYear");
			TextField editBan = (TextField) parentEdit.lookup("#txtBan");
			TextField editGender = (TextField) parentEdit.lookup("#txtGender");
			TextField editKorean = (TextField) parentEdit.lookup("#txtKorean");
			TextField editEnglish = (TextField) parentEdit.lookup("#txtEnglish");
			TextField editMath = (TextField) parentEdit.lookup("#txtMath");
			TextField editSic = (TextField) parentEdit.lookup("#txtSic");
			TextField editSoc = (TextField) parentEdit.lookup("#txtSoc");
			TextField editMusic = (TextField) parentEdit.lookup("#txtMusic");
			TextField editTotal = (TextField) parentEdit.lookup("#txtTotal");
			TextField editAvg = (TextField) parentEdit.lookup("#txtAvg");

			editNo.setDisable(true);
			editName.setDisable(true);
			editYear.setDisable(true);
			editBan.setDisable(true);
			editGender.setDisable(true);
			editTotal.setDisable(true);
			editAvg.setDisable(true);

			editNo.setText(studentEdit.getNo() + "");
			editName.setText(studentEdit.getName());
			editYear.setText(studentEdit.getYear());
			editBan.setText(studentEdit.getBan());
			editGender.setText(studentEdit.getGender());
			editKorean.setText(studentEdit.getKorean() + "");
			editEnglish.setText(studentEdit.getEnglish() + "");
			editMath.setText(studentEdit.getMath() + "");
			editSic.setText(studentEdit.getSic() + "");
			editSoc.setText(studentEdit.getSoc() + "");
			editMusic.setText(studentEdit.getMusic() + "");
			editTotal.setText(studentEdit.getTotal() + "");
			editAvg.setText(studentEdit.getAvg() + "");

			Button btnCal = (Button) parentEdit.lookup("#btnCal");
			Button btnEditAdd = (Button) parentEdit.lookup("#btnEditAdd");
			Button btnFormCancel = (Button) parentEdit.lookup("#btnFormCancel");

			btnEditAdd.setDisable(true);
			// ������ ���� ���
			btnCal.setOnAction(e -> {
				if (editKorean.getText().equals("") || editEnglish.getText().equals("") || editMath.getText().equals("")
						|| editSic.getText().equals("") || editSoc.getText().equals("")
						|| editMusic.getText().equals("")) {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("�л� ���� ����");
					alert.setHeaderText("������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");

					alert.showAndWait();

				} else {

					int sum = Integer.parseInt(editKorean.getText()) + Integer.parseInt(editEnglish.getText())
							+ Integer.parseInt(editMath.getText()) + Integer.parseInt(editSic.getText())
							+ Integer.parseInt(editSoc.getText()) + Integer.parseInt(editMusic.getText());

					double avg = sum / 6.0;

					editTotal.setText(sum + "");
					editAvg.setText(avg + "");

					editKorean.setDisable(true);
					editEnglish.setDisable(true);
					editMath.setDisable(true);
					editSic.setDisable(true);
					editSoc.setDisable(true);
					editMusic.setDisable(true);

					btnCal.setDisable(true);
					btnEditAdd.setDisable(false);
				}
			});

			// ������ ���� ����
			btnEditAdd.setOnAction(e -> {
				
				StudentVO sVo = null;
				StudentDAO sDao = null;

				TextField txtNo = (TextField) parentEdit.lookup("#txtNo");
				TextField txtName = (TextField) parentEdit.lookup("#txtName");
				TextField txtYear = (TextField) parentEdit.lookup("#txtYear");
				TextField txtBan = (TextField) parentEdit.lookup("#txtBan");
				TextField txtGender = (TextField) parentEdit.lookup("#txtGender");
				TextField txtKorean = (TextField) parentEdit.lookup("#txtKorean");
				TextField txtEnglish = (TextField) parentEdit.lookup("#txtEnglish");
				TextField txtMath = (TextField) parentEdit.lookup("#txtMath");
				TextField txtSic = (TextField) parentEdit.lookup("#txtSic");
				TextField txtSoc = (TextField) parentEdit.lookup("#txtSoc");
				TextField txtMusic = (TextField) parentEdit.lookup("#txtMusic");
				TextField txtTotal = (TextField) parentEdit.lookup("#txtTotal");
				TextField txtAvg = (TextField) parentEdit.lookup("#txtAvg");

				data.remove(selectedIndex);

				try {
					sVo = new StudentVO(Integer.parseInt(txtNo.getText()), txtName.getText(), txtYear.getText(),
							txtBan.getText(), txtGender.getText(), Integer.parseInt(txtKorean.getText().trim()),
							Integer.parseInt(txtEnglish.getText().trim()), Integer.parseInt(txtMath.getText().trim()),
							Integer.parseInt(txtSic.getText().trim()), Integer.parseInt(txtSoc.getText().trim()),
							Integer.parseInt(txtMusic.getText().trim()), Integer.parseInt(txtTotal.getText().trim()),
							Double.parseDouble(txtAvg.getText().trim()));
					
					dialog.close();
					
					sDao = new StudentDAO();
					sDao.getStudentUpdate(sVo, sVo.getNo());

					data.removeAll(data);
					totalList();		

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				btnDelete.setDisable(true);
				btnEdit.setDisable(true);

				// �⺻ �̹���
				localUrl = "/image/default.png";
				localImage = new Image(localUrl, false);
				imageView.setImage(localImage);

			});
			
			// ��� ��ư
			btnFormCancel.setOnAction(e -> {
				btnDelete.setDisable(true);
				btnEdit.setDisable(true);
				dialog.close();
			});

			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// �л� �̸����� �˻�
	public void handlerBtnSearchActoion(ActionEvent event) {
		StudentVO sVo = new StudentVO();
		StudentDAO sDao = null;//����Ÿ���̽� ����

		Object[][] totalData = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txtSearch.getText().trim();
			sDao = new StudentDAO();
			sVo = sDao.getStudentCheck(searchName);//�л��̸��� �˻��ؼ� �ش�� ���ڵ峻���� sVo�� �����ؼ� ��������

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�л� ���� �˻�");
				alert.setHeaderText("�л��� �̸��� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");

				alert.showAndWait();
			}

			if (!searchName.equals("") && (sVo != null)) {

				ArrayList<String> title;
				ArrayList<StudentVO> list;

				title = sDao.getColumnName();//�ʵ�� �̸��� ����
				int columnCount = title.size();

				list = sDao.getStudentTotal();//�� ���ڵ带  ArrayList<StudentVO> �������� �����ؼ� ������.
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (sVo.getName().equals(searchName)) {

					txtSearch.clear();
					data.removeAll(data);
					for (int index = 0; index < rowCount; index++) {
						sVo = list.get(index);
						if (sVo.getName().equals(searchName)) {
							data.add(sVo);
							searchResult = true;
						}
					}
				}
			}

			if (!searchResult) {
				txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� ���� �˻�");
				alert.setHeaderText(searchName + " �л��� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");

				alert.showAndWait();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�л� ���� �˻� ����");
			alert.setHeaderText("�л� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");

		}

	}

	// ���� ��Ʈ
	public void handlerBtnPieChartActoion(MouseEvent event) {
		// ���콺 ���� ���� Ŭ�� ī��Ʈ�̸� 2�� ��ȯ
		// ���콺 ���� Ŭ���̸� ���� ����
		if (event.getClickCount() != 2) {
			try {
				selectStudent = tableView.getSelectionModel().getSelectedItems();

				no = selectStudent.get(0).getNo();

				selectFileName = selectStudent.get(0).getFilename();

				localUrl = "file:/C:/images/" + selectFileName;

				localImage = new Image(localUrl, false);

				imageView.setImage(localImage);
				imageView.setFitHeight(250);
				imageView.setFitWidth(230);

				btnEdit.setDisable(false);
				btnDelete.setDisable(false);

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�л�����");
				alert.setHeaderText("������ �л��� �����ϴ�..");
				alert.setContentText("�л��� �߰��� �Ŀ� �����ϼ���");

				alert.showAndWait();

				btnEdit.setDisable(true);
				btnDelete.setDisable(true);
			}

			return;
		}

		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnOk.getScene().getWindow());

			Parent parent = FXMLLoader.load(getClass().getResource("/view/piechart.fxml"));

			PieChart pieChart = (PieChart) parent.lookup("#pieChart");
			StudentVO studentPieChart = tableView.getSelectionModel().getSelectedItem();
			dialog.setTitle(studentPieChart.getName() + " ���� �׷���");
			pieChart.setData(FXCollections.observableArrayList(new PieChart.Data("����", studentPieChart.getTotal()),
					new PieChart.Data("���", studentPieChart.getAvg())));

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {
		}
	}

	// ���� ��Ʈ
	public void handlerBtnBarChartActoion(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnOk.getScene().getWindow());
			dialog.setTitle("���� �׷���");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");

			// ��������
			XYChart.Series seriesKorean = new XYChart.Series();
			seriesKorean.setName("����");
			ObservableList koreanList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				koreanList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getKorean()));
			}
			seriesKorean.setData(koreanList);
			barChart.getData().add(seriesKorean);

			// ��������
			XYChart.Series seriesMath = new XYChart.Series();
			seriesMath.setName("����");
			ObservableList mathList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				mathList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getMath()));
			}
			seriesMath.setData(mathList);
			barChart.getData().add(seriesMath);

			// ��������
			XYChart.Series seriesEnglish = new XYChart.Series();
			seriesEnglish.setName("����");
			ObservableList englishList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				englishList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getEnglish()));
			}
			seriesEnglish.setData(englishList);
			barChart.getData().add(seriesEnglish);

			// ���� ����
			XYChart.Series seriesSic = new XYChart.Series();
			seriesSic.setName("����");
			ObservableList sicList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				sicList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getSic()));
			}
			seriesSic.setData(sicList);
			barChart.getData().add(seriesSic);

			// ��ȸ ����
			XYChart.Series seriesSoc = new XYChart.Series();
			seriesSoc.setName("��ȸ");
			ObservableList socList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				socList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getSoc()));
			}
			seriesSoc.setData(socList);
			barChart.getData().add(seriesSoc);

			// ���� ����
			XYChart.Series seriesMusic = new XYChart.Series();
			seriesMusic.setName("����");
			ObservableList musicList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				musicList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getMusic()));
			}
			seriesMusic.setData(musicList);
			barChart.getData().add(seriesMusic);

			// ����Ʈ ����
			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {
		}
	}

	// �����ư
	public void handlerBtnExitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	// �̸� �ؽ�Ʈ �ʵ忡 ���콺 Ŭ���� ��� �ʱ�ȭ
	public void handlerTxtNameActoion(MouseEvent event) {
		init();
	}

	// �ʱ�ȭ ��ư
	public void handlerBtnInitActoion(ActionEvent event) {
		init();
	}

	// ���� �հ�
	public void handlerBtnSumAction(ActionEvent event) {
		try {
			if (txtName.getText().equals("") || txtBan.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� �Է�");
				alert.setHeaderText("�л��� �̸��� ���� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
	
				alert.showAndWait();
			}
			int korean = Integer.parseInt(txtKo.getText().trim());
			int english = Integer.parseInt(txtEng.getText().trim());
			int math = Integer.parseInt(txtMath.getText().trim());
			int sic = Integer.parseInt(txtSic.getText().trim());
			int soc = Integer.parseInt(txtSoc.getText().trim());
			int music = Integer.parseInt(txtMusic.getText().trim());
			int total;
	
			total = korean + english + math + sic + soc + music;
	
			student.setName(txtName.getText());
			student.setYear(cbYear.getItems() + "");
			student.setBan(txtBan.getText());
			student.setGender(genderGroup.getUserData() + "");
			student.setKorean(korean);
			student.setEnglish(english);
			student.setMath(math);
			student.setSic(sic);
			student.setSoc(soc);
			student.setMusic(music);
			student.setTotal(total);
	
			txtTotal.setText(student.getTotal() + "");
			btnAvg.setDisable(false);
			btnSum.setDisable(true);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText("������ �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// ���
	public void handlerBtnAvgActoion(ActionEvent event) {
		student.setAvg(student.getTotal() / 6.0);
		txtAvg.setText(student.getAvg() + "");
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
		btnImageFile.setDisable(false);

		// ���� ��ư ���� ����
		if (editDelete == false) {
			btnOk.setDisable(true);
		}
	}
}
