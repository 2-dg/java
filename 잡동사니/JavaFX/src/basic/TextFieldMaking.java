package basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextFieldMaking extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Eclipse���� ����� ���
//		HBox root = new HBox();
//		root.setPadding(new Insets(10));
//		TextField textfield = new TextField();
//		textfield.setPrefWidth(200);
//		Button button = new Button();
//		button.setText("Check");
//		root.getChildren().add(textfield);
//		root.getChildren().add(button);
//		root.setSpacing(10);

		
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		//������ fxml�� �θ������̳ʷ� �ε���.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Title");
		
	}
}