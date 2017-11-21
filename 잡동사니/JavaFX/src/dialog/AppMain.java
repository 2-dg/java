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
		//�δ��� �ϸ� �δ� �ȿ� ����� RootController�� ������ �� ����.
		Parent root = loader.load();
		RootController rootController = loader.getController();
		rootController.setStage(ps);
		//�̷��� �޼ҵ带 ���� RootController�� PrimaryStage�� ������ �� �ִ�.
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setTitle("TITLE");
		ps.show();		
	}
}