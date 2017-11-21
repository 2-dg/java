package primary_stage_sharing;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{
	private static Stage ps;
	//static선언은 동일 클래스 바탕으로 만들어진 객체들이 공유하는 유일한 자원이며
	//외부 접근은 불가능하다. 그러므로 함수를 만들어서 전달시키는 것.
	@FXML private Button btnOpenDirectory;
	@FXML private Button btnOpenFile;
	@FXML private Button btnCustom;
	@FXML private Button btnPopup;
	//결론 : 메인스테이지에서 기능을 수행할 애들은 FXID를 부여하고
	//팝업이나 서브스테이지에서 기능을 수행할 애들은 그냥 ID를 부여해서 수행한다.
	
	public void setStage(Stage ps) {
		//System.out.println("루트컨트롤러 생성자실행");
		this.ps=ps;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOpenDirectory.setOnAction((event)->handleOpenDirectory(event));
		btnOpenFile.setOnAction((event)-> handleOpenFile(event));
		btnCustom.setOnAction((event)-> {
			try {
				handleCustom(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		btnPopup.setOnAction((event)->{
			try {
				handleOpenPopup(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void handleOpenPopup(ActionEvent event) throws IOException {
		Popup popup = new Popup();
		//팝업은 따로 스테이지나 기타 부가설정이 필요하지 않음.
		Parent parent = FXMLLoader.load(getClass().getResource("popup.fxml"));
		popup.getContent().add(parent);
		popup.show(ps);
		popup.setAutoHide(true);
		ImageView imageView = (ImageView) parent.lookup("#imgMassage");
		imageView.setImage(new Image(getClass().getResource("images./dialog-info.png").toString()));
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popup.hide();
				//메인창을 눌렀을 때 popup창이 사라짐.
			}
		});
		
		Label label = (Label) parent.lookup("#lblMsg");
		label.setText(btnPopup.getText());
		
		
	}

	public void handleOpenFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		//일반방법
		ObservableList<ExtensionFilter> list = FXCollections.observableArrayList();
		list.add(new ExtensionFilter("Text Files", "*.txt"));
		list.add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		list.add(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
		list.add(new ExtensionFilter("All Files", "*.*"));
		fileChooser.getExtensionFilters().addAll(list);
		
		//짧은 방법
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),		
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),		
				new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),		
				new ExtensionFilter("All Files", "*.*")
				);
		File selectedFile = fileChooser.showOpenDialog(ps);
		//보면 showOpenDialog라는 함수의 실행 매개변수가 OwnerWindow라고 나오는데
		//말 그대로 소유하고있는 윈도우 창 위에 이 다이얼로그를 뜨게 하는 것이다.
		File selectedFile2 = fileChooser.showOpenDialog((Stage)btnOpenFile.getScene().getWindow());
		//식으로도 가능하다. 하지만 이 방법은 그 스테이지 안에 컨트롤러 하나는 포함돼야 한다.
		//하지만 첫번째 방법처럼 사전작업이 많이 필요하진 않다.
		//System.out.println(selectedFile.getPath());
	}

	public void handleOpenDirectory(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDir = directoryChooser.showDialog(ps);
		//System.out.println(selectedDir.getPath());
	}
	public void handleCustom(ActionEvent event) throws IOException {
		Stage customStage = new Stage(StageStyle.UTILITY);
		//커스텀스테이지는 소유스테이지가 프라이머리스테이지인 것을 꼭 밝혀줘야 한다.
		//이 커스텀스테이지는 부무대가 되는 것이고 이렇게 하면 설정이 가능해진다.
		customStage.initModality(Modality.WINDOW_MODAL);
		//커스텀스테이지를 모달창으로 설정.
		customStage.initOwner(ps);
		//OwnerStage를 primaryStage로 설정
		customStage.initOwner((Stage)btnCustom.getScene().getWindow());
		//식으로도 또 가능하다.
		//여기까지만 커스텀 다이얼로그로서 필요한 기능이고 나머지 셋팅하는 건 PrimaryStage와 완전히 같다.
		
		Parent parent = FXMLLoader.load(getClass().getResource("custom.fxml"));
		//여기에서 custom.fxml에서 선언한 id들이 넘어오는 것이다.
		Label labelToMain = (Label)parent.lookup("#labelToMain");
		//custom.fxml에 부여한 id를 찾아서 객체참조변수에 전달하는 것
		//그리고 이것을 이 메소드가 포함된 밖에서 선언하면 밖에서도 공유가 가능하다.
		labelToMain.setText(btnCustom.getText());
		TextField txtField = (TextField) parent.lookup("#txtField");
		Button btnOk = (Button) parent.lookup("#btnOk");
		btnOk.setOnAction((ActionEvent e)-> {
				btnCustom.setText(txtField.getText());
				customStage.close();
		});
		//또 이렇게 매개변수 이름을 바꾸니까 된다. 이 메소드들이 포함돼있는 handleCustom메소드 자체의
		//매개변수명이 event이므로 위 람다식에서 받지 못한다.
		
		Scene scene = new Scene(parent);
		customStage.setScene(scene);
		//PrimaryStage와 설정하는 방법이 비슷비슷허다.
		customStage.setTitle("CustomStage");
		customStage.setResizable(false);
		//창 크기조절 불가
		customStage.show();
	}
}
//이 과정에서 중요한 건 다른 명령어들이 아니라 어떻게 해야 다이얼로그나 다른 창들을 실행하는 와중에
//메인스테이지를 띄우느냐이다. 그리고 또 SubStage에서 OwnerStage로 정보를 전달하는 방법이 이 과정의 키포인트이다.
//결국 OwnerStage와 SubStage의 정보교환, 그리고 그 Stage끼리와 DB사이에서의 정보교환이 중요하다.
