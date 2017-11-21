package Controller;
 
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import javafx.stage.Stage;
 
public class MainController implements Initializable {
	@FXML private Button btnLogin;
	@FXML private Button btnExit;

    public void LOGIN() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
		Stage window = (Stage)( btnLogin.getScene().getWindow());
		window.close();

    }
    public void handleBtnExit() {
    	Platform.exit();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}    
}