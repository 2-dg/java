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
		//root.fxml�� ������ Ŭ�����̰� �δ��� ���� ���������� ��üȭ�ؼ� �������� ���̴�.
		//�׸��� root.fxml�� �������� �� �ȿ� ������ѵ� RootController�� �����´�.
		//�׸��� RootController�� Initialize�ȿ� �����ص� �̺�Ʈ���� ����ϰ� �ִ� ���̴�.
		Scene scene = new Scene(root);
		//�������� ��üȭ��Ű�� ������ �װ��� ������ ���� ���µ� Scene�� ���ؼ� �װ��� ������Ʈ�ϴ� ���̴�.
		
		
		ps.setScene(scene);		
		ps.setTitle("TITLE");
		ps.show();		
	}
}