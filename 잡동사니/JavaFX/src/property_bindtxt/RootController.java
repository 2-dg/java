package property_bindtxt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class RootController implements Initializable{
	@FXML private TextArea taMsg1;
	@FXML private TextArea taMsg2;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//taMsg2.textProperty().bind(taMsg1.textProperty());
		taMsg2.textProperty().bindBidirectional(taMsg1.textProperty());
		
		
		
	}
	
	
}