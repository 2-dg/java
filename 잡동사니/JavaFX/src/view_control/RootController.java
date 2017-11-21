package view_control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable{
	@FXML private ListView<String> listView;
	@FXML private TableView<Phone> tableView;
	@FXML private ImageView imageView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//����Ʈ�信 �߰�
		ObservableList<String> list =  FXCollections.observableArrayList();
		list.add("������S1");
		list.add("������S2");
		list.add("������S3");
		list.add("������S4");
		list.add("������S5");
		list.add("������S6");
		list.add("������S7");
		listView.setItems(list);
		//FXCollections�� JavaFX���� �� �� �ְ� �÷����� �����ϴ� Ŭ�����̴�.
		//ObservableList�� �� �״�� ��ȭ�� ������ �� �ְ� ����Ʈ�� �����ϰ� �ϴ� ��.
		//���׸����� ������ֱ� ������ � Ŭ������ �ͼ� �÷����� ���� �� �ְ� �Ѵ�.
		//setItems�� list�� �����س��� �÷����� ����� listView���ٰ� �־��ִ� ��. 
		
		//���̺�信 �߰�
		//1�ܰ� : ���̺�信 ���� ���� Ŭ���� ����
		//2�ܰ� : ���̺�信 ����
		ObservableList<Phone> tablelist = FXCollections.observableArrayList();
		tablelist.add(new Phone("������S1","phone01.png"));
		tablelist.add(new Phone("������S2","phone02.png"));
		tablelist.add(new Phone("������S3","phone03.png"));
		tablelist.add(new Phone("������S4","phone04.png"));
		tablelist.add(new Phone("������S5","phone05.png"));
		tablelist.add(new Phone("������S6","phone06.png"));
		tablelist.add(new Phone("������S7","phone07.png"));
		//3�ܰ� : ���̺�信 ����ִ� �÷��� PhoneŬ���� ��������� ��ġ
		//���ϸ�����
		
		TableColumn tcSmartPhone = tableView.getColumns().get(0);
		//0�� �÷���ü�� �̸����� �����ϱ� ���� ������
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		//�� ��ü�� smartPhone�̶�� ���������� ��ġ��Ŵ. ���� Ŭ�������� ����������� �о
		//�� ��������� �ش��ϴ� ������ �Ӽ��鿡 �߰������ִ� ��.
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");
		//�Ӽ����� ��ġ�� ����� ��.
		TableColumn tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
		tcImage.setStyle("-fx-alignment: CENTER;");
		tableView.setItems(tablelist);
		
		//==========================�̺�Ʈ���===============================
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				//Ŭ������ȭ
				tableView.scrollTo(newValue.intValue());
				//��ũ�� ����ȭ
			}
		});
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
				imageView.setImage(new Image(getClass().getResource("images/"+newValue.getImage()).toString()));
				//�̹�������ȭ
//				listView.getSelectionModel().select(newValue.getSmartPhone());
//				listView.scrollTo(newValue.getSmartPhone());
			}
		});	
	}
	public void handleBtnOkAction() {
		String item = listView.getSelectionModel().getSelectedItem();
		System.out.println("ListView ����Ʈ�� : "+item);
		Phone phone = tableView.getSelectionModel().getSelectedItem();
		System.out.println("TableView ����Ʈ�� : "+phone.getSmartPhone());
		System.out.println("TableView �̹��� : "+phone.getImage());	
	}
	public void handleBtnCancleAction() {
		Platform.exit();
	}
}