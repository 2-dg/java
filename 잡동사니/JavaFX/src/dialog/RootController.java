package dialog;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class RootController implements Initializable{
	public static Stage ps;
	//static선언은 동일 클래스 바탕으로 만들어진 객체들이 공유하는 유일한 자원이며
	//외부 접근은 불가능하다. 그러므로 함수를 만들어서 전달시키는 것.
	@FXML private Button btnOpenDirectory;
	@FXML private Button btnOpenFile;
	
	public void setStage(Stage ps) {
		this.ps=ps;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOpenDirectory.setOnAction((event)->handleOpenDirectory(event));
		btnOpenFile.setOnAction((event)-> handleOpenFile(event));
		
	}

	protected void handleOpenFile(ActionEvent event) {
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
		String selectedFilePath = selectedFile.getPath();
		System.out.println(selectedFilePath);
	}

	protected void handleOpenDirectory(ActionEvent event) {
		System.out.println("디렉토리");
		
	}	
}