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
	//static������ ���� Ŭ���� �������� ������� ��ü���� �����ϴ� ������ �ڿ��̸�
	//�ܺ� ������ �Ұ����ϴ�. �׷��Ƿ� �Լ��� ���� ���޽�Ű�� ��.
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
		//�Ϲݹ��
		ObservableList<ExtensionFilter> list = FXCollections.observableArrayList();
		list.add(new ExtensionFilter("Text Files", "*.txt"));
		list.add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		list.add(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
		list.add(new ExtensionFilter("All Files", "*.*"));
		fileChooser.getExtensionFilters().addAll(list);
		
		//ª�� ���
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
		System.out.println("���丮");
		
	}	
}