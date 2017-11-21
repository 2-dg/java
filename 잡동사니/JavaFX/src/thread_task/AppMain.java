package thread_task;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage ps) throws Exception {		
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		ps.setScene(scene);		
		ps.setTitle("TITLE");
		ps.show();		
	}
}