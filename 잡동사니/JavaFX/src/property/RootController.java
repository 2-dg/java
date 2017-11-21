package property;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

public class RootController implements Initializable{
	@FXML private Label lbMsg;
	@FXML private Label lbSize;
	@FXML private Slider slider;
	@FXML private ProgressIndicator piDisplay;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			System.out.println("���� �� : "+oldValue.toString()+"\t�ٲ� �� : "+newValue.toString());
			lbMsg.setFont(new Font(newValue.doubleValue()));
			lbSize.setText("Font Size : "+newValue.intValue());
			piDisplay.setProgress(newValue.doubleValue()/100);
			//ProgressIndicator�� �ִ��� 1.0�̱� ������ /100�� ����� ��.
			System.out.println(observable.getValue().toString());
			}
		});
	}
	
}