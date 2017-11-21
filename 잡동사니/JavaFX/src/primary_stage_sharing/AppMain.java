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
		//�δ��� �ϸ� �δ� �ȿ� ����� RootController�� ������ �� ����.
		//�̷��� �ϸ� fxml�ȿ� �ִ� ��� ������� ��üȭ��ų �� ����. ���� �� �ȿ� ���Ե� ��Ʈ ��Ʈ�ѷ��� ���Ե�.
		Parent root = loader.load();
		RootController rootController = loader.getController();
		//�������� ��Ʈ ��ü�� ��Ʈ�ѷ��� �߰���Ų��.
		//�δ����� �ҷ��� fxml�ȿ� ��Ʈ�ѷ��� ��ΰ� �ְ� �װ��� ���⿡�� �����Ű�� ���̴�.
		rootController.setStage(ps);
		//�̷��� �޼ҵ带 ���� RootController�� PrimaryStage�� ������ �� �ִ�.
		//�� ������ ��ġ�� ��Ʈ ��Ʈ�ѷ��� ���� �����ڰ� ����Ǵµ� �̷��� �Ű����������� primary stage�� ����.
		//���⿡�� ��Ʈ��Ʈ�ѷ��� �����ڰ� ����ȴ�.
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setResizable(false);
		ps.setTitle("TITLE");
		ps.show();
		//PrimaryStage�� ����Ʈ���� DECORATED�� setResizable(true)�̴�.
	}
}