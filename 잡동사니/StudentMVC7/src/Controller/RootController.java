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
	ObservableList<StudentVO> selectStudent = null; // 테이블에서 선택한 정보 저장
	StudentVO student = new StudentVO(); // 총점 평균 계산

	boolean editDelete = true; // 확인 버튼 상태 설정
	int selectedIndex; // 테이블에서 선택한 학생 정보 인덱스 저장

	private Stage primaryStage;
	String selectFileName = ""; // 이미지 파일명
	String localUrl = ""; // 이미지 파일 경로
	Image localImage;

	int no; // 삭제시 테이블에서 선택한 학생의 번호 저장
	File selectedFile = null;

	// 이미지 처리 ( 이미지 저장할 폴더를 매개변수로 파일 객체 생성)-
	// 
	private File dirSave = new File("C:/images");
	// 이미지 불러올 파일을 저장할 파일 객체 선언
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

		// 학년 콤보 값 설정
		cbYear.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));

		// 과목 점수필드에 숫자만 입력
		setTextFormatterFunction();

		//테이블뷰에  StudentVO 객체와 연결함.
		setTableColumnValueFactoryFunction();

		// 학생 전체 정보  (레코드에 있는 학생정보를 가져와서  ArrayList<StudentVO> list에 StudentVO로 변환해서 data에 넣어주는 함수)
		totalList();
		
		//테이블뷰에 ObservableList<StudentVO> data값을 지정함.
		tableView.setItems(data);

		// 기본 이미지
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);//backgroundLoading => false
		imageView.setImage(localImage);
		
		btnTotalList.setOnAction(event -> handlerBtnTotalListActoion(event)); // 전체버튼리스트 모든것을지우고 다시리셋
		btnSum.setOnAction(event -> handlerBtnSumAction(event));//총점을 구하는 리스트
		btnOk.setOnAction(event -> handlerBtnOkAction(event));//학생 정보를 저장
		txtName.setOnMouseClicked(event -> handlerTxtNameActoion(event)); //이름을 클릭하면 초기화
		btnAvg.setOnAction(event -> handlerBtnAvgActoion(event)); // 평균 계산
		btnInit.setOnAction(event -> handlerBtnInitActoion(event)); // 초기화
		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // 종료
		btnSearch.setOnAction(event -> handlerBtnSearchActoion(event)); // 학생이름으로  검색																	
		btnEdit.setOnAction(event -> handlerBtnEditActoion(event)); // 학생 정보 수정
		btnDelete.setOnAction(event -> handlerBtnDeleteActoion(event)); // 학생 정보삭제
		btnBarChart.setOnAction(event -> handlerBtnBarChartActoion(event)); // 바차트
		tableView.setOnMouseClicked(event -> handlerBtnPieChartActoion(event)); // 테이블의 학생 선택
		btnImageFile.setOnAction(event -> handlerBtnImageFileActoion(event)); // 이미지 선택창
																				
	}
	

	public void setTableColumnValueFactoryFunction(){
		tableView.setEditable(false);

		// 테이블 뷰 컬럼이름 설정
		TableColumn colNo = new TableColumn("NO.");
		colNo.setMaxWidth(40);
		colNo.setStyle("-fx-allignment: CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn colName = new TableColumn("성명");
		colName.setMaxWidth(60);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colLevel = new TableColumn("학년");
		colLevel.setMaxWidth(40);
		colLevel.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn colBan = new TableColumn("반");
		colBan.setMaxWidth(40);
		colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));

		TableColumn colGender = new TableColumn("성별");
		colGender.setMaxWidth(40);
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn colKorean = new TableColumn("국어");
		colKorean.setMaxWidth(40);
		colKorean.setCellValueFactory(new PropertyValueFactory<>("korean"));

		TableColumn colEnglish = new TableColumn("영어");
		colEnglish.setMaxWidth(40);
		colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));

		TableColumn colMath = new TableColumn("수학");
		colMath.setMaxWidth(40);
		colMath.setCellValueFactory(new PropertyValueFactory<>("math"));

		TableColumn colSic = new TableColumn("과학");
		colSic.setMaxWidth(40);
		colSic.setCellValueFactory(new PropertyValueFactory<>("sic"));

		TableColumn colSoc = new TableColumn("사회");
		colSoc.setMaxWidth(40);
		colSoc.setCellValueFactory(new PropertyValueFactory<>("soc"));

		TableColumn colMusic = new TableColumn("음악");
		colMusic.setMaxWidth(40);
		colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));

		TableColumn colTotal = new TableColumn("총점");
		colTotal.setMaxWidth(40);
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		TableColumn colAvg = new TableColumn("평균");
		colAvg.setMaxWidth(50);
		colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));

		TableColumn colRegister = new TableColumn("등록일");
		colRegister.setMinWidth(90);
		colRegister.setCellValueFactory(new PropertyValueFactory<>("register"));

		TableColumn colImageFileName = new TableColumn("이미지");
		colImageFileName.setMinWidth(260);
		colImageFileName.setCellValueFactory(new PropertyValueFactory<>("filename"));

		tableView.getColumns().addAll(colNo, colName, colLevel, colBan, colGender, colKorean, colEnglish, colMath,
				colSic, colSoc, colMusic, colTotal, colAvg, colRegister, colImageFileName);
		
		
	}
	
	
	public void setTextFormatterFunction(){
		DecimalFormat format = new DecimalFormat( "###" );//3자리포맷
		txtKo.setTextFormatter( new TextFormatter<>(event ->
		{
		    if ( event.getControlNewText().isEmpty() )//입력한 값이 없을때
		    {
		        return event;
		    }

		    ParsePosition parsePosition = new ParsePosition( 0 );//위치지정
			
		//문자열로 입력한값이 "###" 3자리 숫자형식으로 변환함. 예)a12 -> 3자리 숫자형식으로 바꿀수 없으면 null됨
		    Object object = format.parse( event.getControlNewText(), parsePosition );
			
		    //변환된값이 null(숫자아닌 문자나 특수문자를 클릭하면 null을 리턴해서 해당된 텍스트에 해당문자를 써주지않음)
		    // 이거나 변환된위치의 길이가 실제길이보다 적거나, 
		    //실제 입력한 길이가 4이면 null 아니면 해당된값으로 리턴함.	
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
	
	// 수정 창
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/***********************************************************
	 * imageDelete() 이미지 삭제 메소드.
	 * 
	 * @param (삭제할
	 *            파일명)
	 * @return 삭제여부를 리턴
	 ***********************************************************/
	public boolean imageDelete(String fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + fileName); // 삭제 이미지 파일  
																						
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();

				// 기본 이미지
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
	 * imageSave() 이미지 저장 메소드.
	 * 
	 * @param (읽어올
	 *            파일 객체)
	 ***********************************************************/
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "student" + System.currentTimeMillis() + "_" + file.getName();

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));
			//System.out.println(dirSave.getAbsolutePath() + "\\" + fileName);
			
			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
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

	// 학생 전체 리스트 (레코드에 있는 학생정보를 가져와서  ArrayList<StudentVO> list에 StudentVO로 변환해서 data에 넣어주는 함수)
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

	// 초기화 메소드
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

		// 기본 이미지
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);
	}

	// 저장
	private void handlerBtnOkAction(ActionEvent event) {
			/*if (btnEdit.getOnAction().equals(false)) {
			System.out.println("수정버튼 상태");
		}*/
		if (txtName.getText().equals("") || txtBan.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText("학생의 이름과 반을 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
	
			alert.showAndWait();
		}
	
		data.removeAll(data);		
		System.out.println("btnOk에서 사이즈 ="+data.size());// 0이  출력함
	
		StudentVO sVo = null;
		StudentDAO sDao = new StudentDAO();//데이타베이스  처리하는 객체생성
	
		// 폴더 생성 File dirSave =new File("C:/images");
		try {
			File dirMake = new File(dirSave.getAbsolutePath());
			// 이미지 저장 폴더 없으면 생성
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
		} catch (Exception ie) {
			System.out.println("ie = [ " + ie.getMessage() + "]");
		}
	
		// 이미지 파일 저장
		String fileName = imageSave(selectedFile);//이미지 파일버튼 클릭을 통하여 해당된 파일 selectedFile에 저장되어있음. 
	
		// 학생 정보 저장
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
				sDao.getStudentregiste(sVo);//테이블에 삽입실행(insert 진행)
	
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("정보 입력");
				alert.setHeaderText("학생의 성별을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
	
				alert.showAndWait();
			}
			if (sDao != null) {
	
				totalList();//학생정보 테이블에서 가져와서 data에 모두 삽입
	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 점수 입력");
				alert.setHeaderText(txtName.getText() + " 학생의 점수가 성공적으로 추가되었습니다..");
				alert.setContentText("다음 학생의 점수을 입력하세요");
				alert.showAndWait();
	
				btnImageFile.setDisable(true);//버튼이미지파일 비활성화
	
				// 기본 이미지
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
				
				handlerBtnInitActoion(event);//화면 초기화처리함.
			}
		}

	}

	//
	protected void handlerBtnTotalListActoion(ActionEvent event) {
		try {
			data.removeAll(data);
			totalList();// 학생 전체 정보
		} catch (Exception e) {	}
		
	}

	// 이미지 파일 선택 창
	public void handlerBtnImageFileActoion(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// 이미지 파일 경로
				// URL은 웹 주소.
		        // URI는 네트워크에서 유니크한 무언가를 구분하기 위해서, 서버프로그램에서 자주 사용됨
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

	// 학생 삭제
	public void handlerBtnDeleteActoion(ActionEvent event) {

		StudentDAO sDao = null;
		sDao = new StudentDAO();

		try {
			sDao.getStudentDelete(no);
			data.removeAll(data);
			// 학생 전체 정보
			totalList();
			// 이미지 파일 삭제
			imageDelete(selectFileName);

			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editDelete = true;
	}

	// 점수 수정
	public void handlerBtnEditActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/View/formedit.fxml"));
			
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnOk.getScene().getWindow());
			dialog.setTitle("수정");

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
			// 수정된 점수 계산
			btnCal.setOnAction(e -> {
				if (editKorean.getText().equals("") || editEnglish.getText().equals("") || editMath.getText().equals("")
						|| editSic.getText().equals("") || editSoc.getText().equals("")
						|| editMusic.getText().equals("")) {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("학생 점수 수정");
					alert.setHeaderText("점수를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");

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

			// 수정된 점수 저장
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

				// 기본 이미지
				localUrl = "/image/default.png";
				localImage = new Image(localUrl, false);
				imageView.setImage(localImage);

			});
			
			// 취소 버튼
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

	// 학생 이름으로 검색
	public void handlerBtnSearchActoion(ActionEvent event) {
		StudentVO sVo = new StudentVO();
		StudentDAO sDao = null;//데이타베이스 연결

		Object[][] totalData = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txtSearch.getText().trim();
			sDao = new StudentDAO();
			sVo = sDao.getStudentCheck(searchName);//학생이름을 검색해서 해당된 레코드내용을 sVo에 저장해서 리턴해줌

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("학생 정보 검색");
				alert.setHeaderText("학생의 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");

				alert.showAndWait();
			}

			if (!searchName.equals("") && (sVo != null)) {

				ArrayList<String> title;
				ArrayList<StudentVO> list;

				title = sDao.getColumnName();//필드명 이름들 저장
				int columnCount = title.size();

				list = sDao.getStudentTotal();//총 레코드를  ArrayList<StudentVO> 형식으로 저장해서 리턴함.
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
				alert.setTitle("학생 정보 검색");
				alert.setHeaderText(searchName + " 학생이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");

				alert.showAndWait();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("학생 정보 검색 오류");
			alert.setHeaderText("학생 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");

		}

	}

	// 파이 차트
	public void handlerBtnPieChartActoion(MouseEvent event) {
		// 마우스 왼쪽 더블 클릭 카운트이면 2을 반환
		// 마우스 왼쪽 클릭이면 수정 삭제
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
				alert.setTitle("학생선택");
				alert.setHeaderText("선택한 학생이 없습니다..");
				alert.setContentText("학생을 추가한 후에 선택하세요");

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
			dialog.setTitle(studentPieChart.getName() + " 파이 그래프");
			pieChart.setData(FXCollections.observableArrayList(new PieChart.Data("총점", studentPieChart.getTotal()),
					new PieChart.Data("평균", studentPieChart.getAvg())));

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {
		}
	}

	// 막대 차트
	public void handlerBtnBarChartActoion(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnOk.getScene().getWindow());
			dialog.setTitle("막대 그래프");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");

			// 국어점수
			XYChart.Series seriesKorean = new XYChart.Series();
			seriesKorean.setName("국어");
			ObservableList koreanList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				koreanList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getKorean()));
			}
			seriesKorean.setData(koreanList);
			barChart.getData().add(seriesKorean);

			// 수학점수
			XYChart.Series seriesMath = new XYChart.Series();
			seriesMath.setName("수학");
			ObservableList mathList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				mathList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getMath()));
			}
			seriesMath.setData(mathList);
			barChart.getData().add(seriesMath);

			// 영어점수
			XYChart.Series seriesEnglish = new XYChart.Series();
			seriesEnglish.setName("영어");
			ObservableList englishList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				englishList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getEnglish()));
			}
			seriesEnglish.setData(englishList);
			barChart.getData().add(seriesEnglish);

			// 과학 점수
			XYChart.Series seriesSic = new XYChart.Series();
			seriesSic.setName("과학");
			ObservableList sicList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				sicList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getSic()));
			}
			seriesSic.setData(sicList);
			barChart.getData().add(seriesSic);

			// 사회 점수
			XYChart.Series seriesSoc = new XYChart.Series();
			seriesSoc.setName("사회");
			ObservableList socList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				socList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getSoc()));
			}
			seriesSoc.setData(socList);
			barChart.getData().add(seriesSoc);

			// 음악 점수
			XYChart.Series seriesMusic = new XYChart.Series();
			seriesMusic.setName("음악");
			ObservableList musicList = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				musicList.add(new XYChart.Data(data.get(i).getName(), data.get(i).getMusic()));
			}
			seriesMusic.setData(musicList);
			barChart.getData().add(seriesMusic);

			// 바차트 종료
			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {
		}
	}

	// 종료버튼
	public void handlerBtnExitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	// 이름 텍스트 필드에 마우스 클릭할 경우 초기화
	public void handlerTxtNameActoion(MouseEvent event) {
		init();
	}

	// 초기화 버튼
	public void handlerBtnInitActoion(ActionEvent event) {
		init();
	}

	// 점수 합계
	public void handlerBtnSumAction(ActionEvent event) {
		try {
			if (txtName.getText().equals("") || txtBan.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("정보 입력");
				alert.setHeaderText("학생의 이름과 반을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
	
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
			alert.setTitle("점수 입력");
			alert.setHeaderText("점수를 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 평균
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

		// 수정 버튼 상태 설정
		if (editDelete == false) {
			btnOk.setDisable(true);
		}
	}
}
