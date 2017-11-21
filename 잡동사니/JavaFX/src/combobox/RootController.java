package combobox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class RootController implements Initializable{
	@FXML private ComboBox<String> combbox;
	@FXML private Label label;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add(new String("A"));
		list.add("B");
		list.add("C");
		list.add("D");
		combbox.setItems(list);
		//root.fxml이 먼저 생성되기 때문에 list가 덮어씌우는 형태가 된다.
		
		combbox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			label.setText("변경된 값 : "+newValue.toString());
			}
		});
	}
}