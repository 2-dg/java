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
		//Eclipse에서 만드는 방법
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
		//다음의 fxml을 부모컨테이너로 로드함.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Title");
		
	}
}