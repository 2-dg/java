package event_handler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMainEventHandler extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage ps) throws Exception {
		//��ü �������		
//		HBox root = new HBox();
//		root.setPrefSize(200, 50);
//		root.setAlignment(Pos.CENTER);
//		root.setSpacing(20);
//		
//		Button btn1 = new Button("��ư1");
//		btn1.setOnAction((event)->System.out.println("��ư 1 Ŭ��"));
//		
//		root.getChildren().add(btn1);
//		//HBox�� ��ư�� �߰�
//		
//		Button btn2 = new Button("��ư2");
//		btn2.setOnAction((event)->System.out.println("��ư 2 Ŭ��"));		
//		
//		root.getChildren().add(btn2);
//		
		
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		Scene scene = new Scene(root);
		ps.setScene(scene);		
		ps.setTitle("TITLE");
		ps.show();
		
		
	}

}
