package menu_bar_and_combo_box;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class RootController implements Initializable{
	@FXML private ComboBox<String> comboBox;
	@FXML private TextArea textArea;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.setItems(FXCollections.observableArrayList(
				new String ("����"),
				new String ("�����"),
				new String ("���� �����"),
				new String ("����"),
				new String ("����")				
				));
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				textArea.appendText(newValue.toString()+"\n");
				switch (newValue.toString()) {
				case "���� �����":
					textArea.appendText("�޺��ڽ� : ");
					handleNew();
					break;
				case "����":
					textArea.appendText("�޺��ڽ� : ");
					handleOpen();
					break;
				case "����":
					textArea.appendText("�޺��ڽ� : ");
					handleSave();
					break;
				default:
					break;
				}
			}
		});
	}
	public void handleNew() {
		textArea.appendText("New\n");
	}
	public void handleOpen() {
		textArea.appendText("Open\n");
	}
	public void handleSave() {
		textArea.appendText("Save\n");
	}
	public void handleExit() {
		Platform.exit();
	}
}