package primary_stage_sharing;

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
		//이렇게 하면 fxml안에 있는 모든 내용들을 객체화시킬 수 있음. 물론 그 안에 포함된 루트 컨트롤러도 포함됨.
		Parent root = loader.load();
		RootController rootController = loader.getController();
		//만들어놓은 루트 객체에 컨트롤러를 추가시킨다.
		//로더에서 불러온 fxml안에 컨트롤러의 경로가 있고 그것을 여기에서 연결시키는 것이다.
		rootController.setStage(ps);
		//이렇게 메소드를 통해 RootController에 PrimaryStage를 전달할 수 있다.
		//이 과정을 거치면 루트 컨트롤러에 대한 생성자가 실행되는데 이렇게 매개변수식으로 primary stage를 전달.
		//여기에서 루트컨트롤러의 생성자가 실행된다.
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setResizable(false);
		ps.setTitle("TITLE");
		ps.show();
		//PrimaryStage는 디폴트값이 DECORATED에 setResizable(true)이다.
	}
}