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
		//객체 생성방식		
//		HBox root = new HBox();
//		root.setPrefSize(200, 50);
//		root.setAlignment(Pos.CENTER);
//		root.setSpacing(20);
//		
//		Button btn1 = new Button("버튼1");
//		btn1.setOnAction((event)->System.out.println("버튼 1 클릭"));
//		
//		root.getChildren().add(btn1);
//		//HBox에 버튼을 추가
//		
//		Button btn2 = new Button("버튼2");
//		btn2.setOnAction((event)->System.out.println("버튼 2 클릭"));		
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
