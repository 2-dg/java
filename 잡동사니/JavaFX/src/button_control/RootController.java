package button_control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RootController implements Initializable{
    @FXML private CheckBox chkBox1;
    @FXML private CheckBox chkBox2;
    @FXML private ImageView img1;
    @FXML private ToggleGroup group;
    @FXML private RadioButton radBtn1;
    @FXML private RadioButton radBtn2;
    @FXML private RadioButton radBtn3;
    @FXML private ImageView img2;
    @FXML private Button btnExit;
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		img2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if(radBtn1.isSelected()) {
					img2.setImage(new Image(getClass().getResource("images/BubbleChart.png").toString()));
					System.out.println(newValue.getUserData().toString());
				}else if(radBtn2.isSelected()) {
					img2.setImage(new Image(getClass().getResource("images/BarChart.png").toString()));
					System.out.println(newValue.getUserData().toString());
				}else if(radBtn3.isSelected()) {
					img2.setImage(new Image(getClass().getResource("images/AreaChart.png").toString()));
					System.out.println(newValue.getUserData().toString());
				}
			}
		});
	}
	public void handleChkAction(ActionEvent e) {
		if(chkBox1.isSelected()&&chkBox2.isSelected()) {
			img1.setImage(new Image(getClass().getResourceAsStream("images/geek-glasses-hair.gif")));
		}else if(chkBox1.isSelected()) {
			img1.setImage(new Image(getClass().getResourceAsStream("images/geek-glasses.gif")));
		}else if(chkBox2.isSelected()) {
			img1.setImage(new Image(getClass().getResourceAsStream("images/geek-hair.gif")));
		}else {
			img1.setImage(new Image(getClass().getResourceAsStream("images/geek.gif")));			
		}
	}
	
	public void handleBtnExit(ActionEvent e) {
		//Platform.exit();
	}
}