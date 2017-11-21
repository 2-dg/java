package css_inline_style;

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
		//root.fxml은 일종의 클래스이고 로더를 통해 힙영역으로 객체화해서 가져오는 것이다.
		//그리고 root.fxml을 가져오면 그 안에 연결시켜둔 RootController도 가져온다.
		//그리고 RootController의 Initialize안에 구현해둔 이벤트들이 대기하고 있는 것이다.
		Scene scene = new Scene(root);
		//페이지를 객체화시키긴 했지만 그것을 투영할 곳이 없는데 Scene을 통해서 그것을 프로젝트하는 것이다.
		
		
		ps.setScene(scene);		
		ps.setTitle("TITLE");
		ps.show();		
	}
}