package dialog;

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
		FXMLLoader loader =  new FXMLLoader(getClass().getResource("root.fxml"));
		//로더를 하면 로더 안에 연결된 RootController를 가져올 수 있음.
		Parent root = loader.load();
		RootController rootController = loader.getController();
		rootController.setStage(ps);
		//이렇게 메소드를 통해 RootController에 PrimaryStage를 전달할 수 있다.
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setTitle("TITLE");
		ps.show();		
	}
}