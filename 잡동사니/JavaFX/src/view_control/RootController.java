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
		//리스트뷰에 추가
		ObservableList<String> list =  FXCollections.observableArrayList();
		list.add("갤럭시S1");
		list.add("갤럭시S2");
		list.add("갤럭시S3");
		list.add("갤럭시S4");
		list.add("갤럭시S5");
		list.add("갤럭시S6");
		list.add("갤럭시S7");
		listView.setItems(list);
		//FXCollections는 JavaFX에서 쓸 수 있게 컬렉션을 구성하는 클래스이다.
		//ObservableList는 말 그대로 변화를 감지할 수 있게 리스트를 구성하게 하는 것.
		//제네릭으로 선언돼있기 때문에 어떤 클래스라도 와서 컬렉션을 만들 수 있게 한다.
		//setItems는 list로 구성해놓은 컬렉션의 목록을 listView에다가 넣어주는 것. 
		
		//테이블뷰에 추가
		//1단계 : 테이블뷰에 넣을 구조 클래스 설계
		//2단계 : 테이블뷰에 삽입
		ObservableList<Phone> tablelist = FXCollections.observableArrayList();
		tablelist.add(new Phone("갤럭시S1","phone01.png"));
		tablelist.add(new Phone("갤럭시S2","phone02.png"));
		tablelist.add(new Phone("갤럭시S3","phone03.png"));
		tablelist.add(new Phone("갤럭시S4","phone04.png"));
		tablelist.add(new Phone("갤럭시S5","phone05.png"));
		tablelist.add(new Phone("갤럭시S6","phone06.png"));
		tablelist.add(new Phone("갤럭시S7","phone07.png"));
		//3단계 : 테이블뷰에 들어있는 컬럼과 Phone클래스 멤버변수와 매치
		//파일명으로
		
		TableColumn tcSmartPhone = tableView.getColumns().get(0);
		//0번 컬럼객체를 이리저리 수정하기 위해 가져옴
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		//그 객체를 smartPhone이라는 멤버변수명과 매치시킴. 만든 클래스에서 멤버변수명을 읽어서
		//그 멤버변수에 해당하는 정보를 속성들에 추가시켜주는 것.
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");
		//속성들의 배치를 가운데로 함.
		TableColumn tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
		tcImage.setStyle("-fx-alignment: CENTER;");
		tableView.setItems(tablelist);
		
		//==========================이벤트등록===============================
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				//클릭동기화
				tableView.scrollTo(newValue.intValue());
				//스크롤 동기화
			}
		});
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
				imageView.setImage(new Image(getClass().getResource("images/"+newValue.getImage()).toString()));
				//이미지동기화
//				listView.getSelectionModel().select(newValue.getSmartPhone());
//				listView.scrollTo(newValue.getSmartPhone());
			}
		});	
	}
	public void handleBtnOkAction() {
		String item = listView.getSelectionModel().getSelectedItem();
		System.out.println("ListView 스마트폰 : "+item);
		Phone phone = tableView.getSelectionModel().getSelectedItem();
		System.out.println("TableView 스마트폰 : "+phone.getSmartPhone());
		System.out.println("TableView 이미지 : "+phone.getImage());	
	}
	public void handleBtnCancleAction() {
		Platform.exit();
	}
}