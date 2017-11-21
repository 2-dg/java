package grid_pane;

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
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}



	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}



	@Override
	public void start(Stage ps) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setTitle("TITLE");
		ps.show();
		
	}

}
