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
				new String ("공개"),
				new String ("비공개"),
				new String ("새로 만들기"),
				new String ("열기"),
				new String ("저장")				
				));
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				textArea.appendText(newValue.toString()+"\n");
				switch (newValue.toString()) {
				case "새로 만들기":
					textArea.appendText("콤보박스 : ");
					handleNew();
					break;
				case "열기":
					textArea.appendText("콤보박스 : ");
					handleOpen();
					break;
				case "저장":
					textArea.appendText("콤보박스 : ");
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