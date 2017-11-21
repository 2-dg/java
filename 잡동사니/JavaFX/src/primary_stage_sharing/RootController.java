package primary_stage_sharing;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{
	private static Stage ps;
	//static������ ���� Ŭ���� �������� ������� ��ü���� �����ϴ� ������ �ڿ��̸�
	//�ܺ� ������ �Ұ����ϴ�. �׷��Ƿ� �Լ��� ���� ���޽�Ű�� ��.
	@FXML private Button btnOpenDirectory;
	@FXML private Button btnOpenFile;
	@FXML private Button btnCustom;
	@FXML private Button btnPopup;
	//��� : ���ν����������� ����� ������ �ֵ��� FXID�� �ο��ϰ�
	//�˾��̳� ���꽺���������� ����� ������ �ֵ��� �׳� ID�� �ο��ؼ� �����Ѵ�.
	
	public void setStage(Stage ps) {
		//System.out.println("��Ʈ��Ʈ�ѷ� �����ڽ���");
		this.ps=ps;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOpenDirectory.setOnAction((event)->handleOpenDirectory(event));
		btnOpenFile.setOnAction((event)-> handleOpenFile(event));
		btnCustom.setOnAction((event)-> {
			try {
				handleCustom(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		btnPopup.setOnAction((event)->{
			try {
				handleOpenPopup(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void handleOpenPopup(ActionEvent event) throws IOException {
		Popup popup = new Popup();
		//�˾��� ���� ���������� ��Ÿ �ΰ������� �ʿ����� ����.
		Parent parent = FXMLLoader.load(getClass().getResource("popup.fxml"));
		popup.getContent().add(parent);
		popup.show(ps);
		popup.setAutoHide(true);
		ImageView imageView = (ImageView) parent.lookup("#imgMassage");
		imageView.setImage(new Image(getClass().getResource("images./dialog-info.png").toString()));
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popup.hide();
				//����â�� ������ �� popupâ�� �����.
			}
		});
		
		Label label = (Label) parent.lookup("#lblMsg");
		label.setText(btnPopup.getText());
		
		
	}

	public void handleOpenFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		//�Ϲݹ��
		ObservableList<ExtensionFilter> list = FXCollections.observableArrayList();
		list.add(new ExtensionFilter("Text Files", "*.txt"));
		list.add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		list.add(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
		list.add(new ExtensionFilter("All Files", "*.*"));
		fileChooser.getExtensionFilters().addAll(list);
		
		//ª�� ���
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),		
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),		
				new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),		
				new ExtensionFilter("All Files", "*.*")
				);
		File selectedFile = fileChooser.showOpenDialog(ps);
		//���� showOpenDialog��� �Լ��� ���� �Ű������� OwnerWindow��� �����µ�
		//�� �״�� �����ϰ��ִ� ������ â ���� �� ���̾�α׸� �߰� �ϴ� ���̴�.
		File selectedFile2 = fileChooser.showOpenDialog((Stage)btnOpenFile.getScene().getWindow());
		//�����ε� �����ϴ�. ������ �� ����� �� �������� �ȿ� ��Ʈ�ѷ� �ϳ��� ���Եž� �Ѵ�.
		//������ ù��° ���ó�� �����۾��� ���� �ʿ����� �ʴ�.
		//System.out.println(selectedFile.getPath());
	}

	public void handleOpenDirectory(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDir = directoryChooser.showDialog(ps);
		//System.out.println(selectedDir.getPath());
	}
	public void handleCustom(ActionEvent event) throws IOException {
		Stage customStage = new Stage(StageStyle.UTILITY);
		//Ŀ���ҽ��������� �������������� �����̸Ӹ����������� ���� �� ������� �Ѵ�.
		//�� Ŀ���ҽ��������� �ι��밡 �Ǵ� ���̰� �̷��� �ϸ� ������ ����������.
		customStage.initModality(Modality.WINDOW_MODAL);
		//Ŀ���ҽ��������� ���â���� ����.
		customStage.initOwner(ps);
		//OwnerStage�� primaryStage�� ����
		customStage.initOwner((Stage)btnCustom.getScene().getWindow());
		//�����ε� �� �����ϴ�.
		//��������� Ŀ���� ���̾�α׷μ� �ʿ��� ����̰� ������ �����ϴ� �� PrimaryStage�� ������ ����.
		
		Parent parent = FXMLLoader.load(getClass().getResource("custom.fxml"));
		//���⿡�� custom.fxml���� ������ id���� �Ѿ���� ���̴�.
		Label labelToMain = (Label)parent.lookup("#labelToMain");
		//custom.fxml�� �ο��� id�� ã�Ƽ� ��ü���������� �����ϴ� ��
		//�׸��� �̰��� �� �޼ҵ尡 ���Ե� �ۿ��� �����ϸ� �ۿ����� ������ �����ϴ�.
		labelToMain.setText(btnCustom.getText());
		TextField txtField = (TextField) parent.lookup("#txtField");
		Button btnOk = (Button) parent.lookup("#btnOk");
		btnOk.setOnAction((ActionEvent e)-> {
				btnCustom.setText(txtField.getText());
				customStage.close();
		});
		//�� �̷��� �Ű����� �̸��� �ٲٴϱ� �ȴ�. �� �޼ҵ���� ���Ե��ִ� handleCustom�޼ҵ� ��ü��
		//�Ű��������� event�̹Ƿ� �� ���ٽĿ��� ���� ���Ѵ�.
		
		Scene scene = new Scene(parent);
		customStage.setScene(scene);
		//PrimaryStage�� �����ϴ� ����� ���������.
		customStage.setTitle("CustomStage");
		customStage.setResizable(false);
		//â ũ������ �Ұ�
		customStage.show();
	}
}
//�� �������� �߿��� �� �ٸ� ��ɾ���� �ƴ϶� ��� �ؾ� ���̾�α׳� �ٸ� â���� �����ϴ� ���߿�
//���ν��������� �������̴�. �׸��� �� SubStage���� OwnerStage�� ������ �����ϴ� ����� �� ������ Ű����Ʈ�̴�.
//�ᱹ OwnerStage�� SubStage�� ������ȯ, �׸��� �� Stage������ DB���̿����� ������ȯ�� �߿��ϴ�.
