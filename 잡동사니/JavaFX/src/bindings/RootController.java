package bindings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class RootController implements Initializable{
	@FXML private Circle circle1;
	@FXML private Circle circle2;
	@FXML private AnchorPane root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		circle1.centerXProperty().bind(Bindings.divide(root.widthProperty(), 2));
		circle1.centerYProperty().bind(Bindings.divide(root.heightProperty(), 2));
		
		
		
	}
	
	
}