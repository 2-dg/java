package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import com.sun.javafx.binding.StringFormatter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Student;

public class RootController implements Initializable{
   
   private static int selectedIndex;
//화면 ID설정=====================================================
   @FXML TableView<Student> tableView=new TableView<>();   //테이블뷰 사용
   @FXML TextField txtName;      //성명
   @FXML ComboBox<String> cbYear;    //학년     
   @FXML TextField txtBan;               // 반
   @FXML ToggleGroup genderGroup;       //남성,여성  성별
   @FXML RadioButton rbMale;             //남성 버튼
   @FXML RadioButton rbFemale;          //여성 버튼
   
   @FXML TextField txtTotal;  //총점
   @FXML Button btnAvg;     //평균
   @FXML Button btnInit;      //초기화
   @FXML Button btnOk;      //확인
   @FXML Button btnExit;     //취소
   @FXML Button btnEdit;     //수정
   @FXML Button btnDelete;  //삭제
   
   @FXML TextField txtKo;          //국어
   @FXML TextField txtEng;         //영어
   @FXML TextField txtMath;        //수학
   @FXML TextField txtSic;           //과학
   @FXML TextField txtSoc;                  
   @FXML TextField txtMusic;        //음악
   @FXML Button btnTotal;          //총점
   @FXML TextField txtAvg;          //평균
   @FXML TextField txtSearch;       //검색
   
   @FXML Button btnSearch;         //검색 버튼
   
   @FXML Button btnBarChart;   //막대바 그래프
   @FXML DatePicker dbDate;  //날짜
//=============================================================

//테이블에 저장될 데이타관리 변수=======================================
   Student student=new Student();  //데이타 저장할 객체생성.
   ObservableList<Student> data=FXCollections.observableArrayList();  //테이블뷰에 연동될 ArrayList
   ObservableList<Student> selectStudent;
   
   
//=============================================================
   
   //확인 버튼 상태 설정
   boolean editDelete=true;

   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
//       버튼 작동진행 방법==============================================
      txtTotal.setEditable(false);   //총점입력란은 편집금지
      txtAvg.setEditable(false); //평균입력란은 편집금지
      
      btnAvg.setDisable(true);   //평균버튼 작동금지
      btnOk.setDisable(true);   //확인버튼 작동금지
      btnEdit.setDisable(true);   //수정버튼 작동금지
      btnDelete.setDisable(true);   //삭제버튼 작동금지
//==============================================================
//숫자 입력만 가능하게 하는 것. 점수가 들어갈 모든 텍스트필드에 적용해야 함.
      DecimalFormat format = new DecimalFormat("###");//숫자만 3자리 받겠다는 말.
      txtKo.setTextFormatter(new TextFormatter<>(event -> {//텍스트필드에 포멧을 지정하겠다
    	  if(event.getControlNewText().isEmpty()) { //입력한 값이 없을 때 실행하는 조건문.
    		  return event;							//getControlNewText는 입력받는 텍스트를 의미
    	  }
    	  ParsePosition parsePosition = new ParsePosition(0);//위치지정
    	  //문자열로 입력한 값을 "###"3자리 숫자형식으로 변환함. 예) a12 -> 3자리 숫자형식으로 바꿀 수 없으면 null이 됨.
    	  Object object = format.parse(event.getControlNewText(),parsePosition);
    	  //입력된 값을 format형식("###")에 맞게 바꿈. 예) 345678이 들어오면 0자리부터 3자리수, 345만 가져옴.
    	  //만약에 a123이 들어온다면 a12만 인식하는데 a가 껴있으므로 null을 반환. 애초에 글씨가 안써짐.
    	  //변환된 값이 null.숫자가 아닌 문자가 들어오면 null을 리턴해서 해당된 텍스트필드에 해당 문자를 입력시키지 않음
    	  //이거나 변환된 위치의 길이가 실제 길이보다 적거나 실제 입력한 길이가 4이면 null 아니면 해당된 값으로 리턴함
    	  if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
    			  ||event.getControlNewText().length()==4) {
    		  return null;
    	  }else {
    		  return event;
    	  }    	  
      }));
      
          
      
      
      
      
      
      //1. 콤보박스에 학년을 삽입해야한다.
//      ObservableList<String> list=FXCollections.observableArrayList();
//      list.add(new String("1"));
//      list.add(new String("2"));
//      list.add(new String("3"));
//      list.add(new String("4"));
//      list.add(new String("5"));
//      list.add(new String("6"));
      
//      학년 콤보박스 데이타 입력처리내용      
      cbYear.setItems(FXCollections.observableArrayList("1","2","3","4","5","6"));
      
//      테이블뷰 데이타 입력처리 내용
      tableView.setEditable(false);
   
//      테이블 컬럼생성 및 해당된 컬럼에 사이즈와 스타일(가운데정렬), 해당클래스와 매칭시킨다.
      TableColumn colName=new TableColumn("성명");
      colName.setMaxWidth(70);
      colName.setStyle("-fx-allignment:CENTER");
      colName.setCellValueFactory(new PropertyValueFactory<>("name"));
      
      TableColumn colLevel=new TableColumn("학년");
      colLevel.setMaxWidth(40);
      colLevel.setStyle("-fx-allignment:CENTER");
      colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
      
      TableColumn colBan=new TableColumn("반");
      colBan.setMaxWidth(40);
      colBan.setStyle("-fx-allignment:CENTER");
      colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));
      
      TableColumn colGender=new TableColumn("성별");
      colGender.setMaxWidth(40);
      colGender.setStyle("-fx-allignment:CENTER");
      colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      
      TableColumn colKorean=new TableColumn("국어");
      colKorean.setMaxWidth(40);
      colKorean.setStyle("-fx-allignment:CENTER");
      colKorean.setCellValueFactory(new PropertyValueFactory<>("korean"));
      
      TableColumn colEnglish=new TableColumn("영어");
      colEnglish.setMaxWidth(40);
      colEnglish.setStyle("-fx-allignment:CENTER");
      colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
      
      TableColumn colMath=new TableColumn("수학");
      colMath.setMaxWidth(40);
      colMath.setStyle("-fx-allignment:CENTER");
      colMath.setCellValueFactory(new PropertyValueFactory<>("math"));
      
      TableColumn colSic=new TableColumn("과학");
      colSic.setMaxWidth(40);
      colSic.setStyle("-fx-allignment:CENTER");
      colSic.setCellValueFactory(new PropertyValueFactory<>("sic"));
      
      TableColumn colSoc=new TableColumn("사회");
      colSoc.setMaxWidth(40);
      colSoc.setStyle("-fx-allignment:CENTER");
      colSoc.setCellValueFactory(new PropertyValueFactory<>("soc"));
      
      TableColumn colMusic=new TableColumn("음악");
      colMusic.setMaxWidth(40);
      colMusic.setStyle("-fx-allignment:CENTER");
      colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));
      
      TableColumn colTotal=new TableColumn("총점");
      colTotal.setMaxWidth(40);
      colTotal.setStyle("-fx-allignment:CENTER");
      colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
      
      TableColumn colAvg=new TableColumn("평균");
      colAvg.setMaxWidth(50);
      colAvg.setStyle("-fx-allignment:CENTER");
      colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
      
      tableView.setItems(data);
      tableView.getColumns().addAll(colName, colLevel, colBan, colGender, colKorean, 
            colEnglish, colMath, colSic, colSoc, colMusic, colTotal, colAvg);
      
//      총점버튼을 눌렀을때 작동하는 이벤트
      btnTotal.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            try{
               handleBtnTotalAction(event);
            }catch(NumberFormatException e){   //덧셈을 하는 도중에 null값이 생기면 <-이에러가 발생한다.
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("점수 입력");
               alert.setHeaderText("점수를 입력하시오.");
               alert.setContentText("다음에는 주의하세요!");
               alert.showAndWait();
            }            
         }
      });      
      btnAvg.setOnAction(event-> handleBtnAvgAction(event));
      btnOk.setOnAction(event-> handlerBtnOkAction(event));
      btnExit.setOnAction(event-> handlerBtnExitAction(event));
      btnInit.setOnAction(event-> handlerBtnInitAction(event));
      btnDelete.setOnAction(event-> handlerBtnDeleteAction(event));
      btnEdit.setOnAction(event-> handlerBtnEditAction(event));
      tableView.setOnMouseClicked(event-> handlerBtnPieChartAction(event));
      btnBarChart.setOnAction((event)-> handlerBtnBarChartAction(event));
      
      tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent me) {
            selectStudent=tableView.getSelectionModel().getSelectedItems();
            selectedIndex=tableView.getSelectionModel().getSelectedIndex();
//            System.out.println("행 번호 : "+selectedIndex);
            txtName.setText(selectStudent.get(0).getName());
            cbYear.setValue(selectStudent.get(0).getLevel());
            
            
         }
      });
      
      btnOk.setOnAction(event-> {
//            if(btnEdit.getOnAction().equals(false)){
//               System.out.println("수정버튼 상태");
//            }
            if(txtName.getText().equals(toString()) || txtBan.getText().equals(toString())){
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("정보 입력");
               alert.setHeaderText("학생의 이름과 반을 입력하시오.");
               alert.setContentText("다음에는 주의하세요!");
               alert.showAndWait();
            }
            
            try{
            data.add(new Student(txtName.getText(), cbYear.getSelectionModel().getSelectedItem(), 
                  txtBan.getText(), genderGroup.getSelectedToggle().getUserData().toString(), 
                  txtKo.getText(), txtEng.getText(), txtMath.getText(), txtSic.getText(), txtSoc.getText(), 
                  txtMusic.getText(), txtTotal.getText(), txtAvg.getText()));
            }catch(Exception e){
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("정보 입력");
               alert.setHeaderText("학생의 성별을 입력하시오.");
               alert.setContentText("다음에는 주의하세요!");
               alert.showAndWait();
            }
            
            txtName.setEditable(true);
            cbYear.getSelectionModel().clearSelection(); 
            txtBan.setEditable(true);
            txtKo.setEditable(true);
            txtEng.setEditable(true);
            txtMath.setEditable(true);
            txtSic.setEditable(true);
            txtSoc.setEditable(true);
            txtMusic.setEditable(true);
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
                  
      });
      
   }

   


   protected void handlerBtnBarChartAction(ActionEvent event) {
	   Stage dialog = new Stage(StageStyle.UTILITY);
	   dialog.initModality(Modality.WINDOW_MODAL);
	   dialog.initOwner(btnOk.getScene().getWindow());
	   dialog.setTitle("막대 그래프");
	   try {
		Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));
		BarChart barChart = (BarChart) parent.lookup("#barChart");

		XYChart.Series seriesKorean = new XYChart.Series();
		seriesKorean.setName("국어");
		ObservableList koreanList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			koreanList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getKorean())));
		}
		seriesKorean.setData(koreanList);
		barChart.getData().add(seriesKorean);
		
		XYChart.Series seriesMath = new XYChart.Series();
		seriesMath.setName("수학");
		ObservableList mathList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			mathList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getMath())));
		}
		seriesMath.setData(mathList);
		barChart.getData().add(seriesMath);
		
		XYChart.Series seriesEnglish = new XYChart.Series();
		seriesEnglish.setName("영어");
		ObservableList englishList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			englishList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getEnglish())));
		}
		seriesEnglish.setData(englishList);
		barChart.getData().add(seriesEnglish);
		
		XYChart.Series seriessic = new XYChart.Series();
		seriessic.setName("과학");
		ObservableList sicList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			sicList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getSic())));
		}
		seriessic.setData(sicList);
		barChart.getData().add(seriessic);
		
		XYChart.Series seriessoc = new XYChart.Series();
		seriessoc.setName("사회");
		ObservableList socList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			socList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getSoc())));
		}
		seriessoc.setData(socList);
		barChart.getData().add(seriessoc);
		
		XYChart.Series seriesMusic = new XYChart.Series();
		seriesMusic.setName("음악");
		ObservableList musicList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			musicList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getMusic())));
		}
		seriesMusic.setData(musicList);
		barChart.getData().add(seriesMusic);
		

	   
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
	    dialog.show();
	    
	    Button btnClose = (Button) parent.lookup("#btnClose");
	    btnClose.setOnAction((e)->dialog.close());
	   
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	
}




private void handlerBtnPieChartAction(MouseEvent event) {
      selectStudent=tableView.getSelectionModel().getSelectedItems();  //선택된 객체
      selectedIndex=tableView.getSelectionModel().getSelectedIndex();  //선택된 위치
      if(event.getClickCount() != 2){  //클릭을 2번 눌렀나?
         btnEdit.setDisable(false);
         btnDelete.setDisable(false);
         return;
      }
      
      try {
         Stage dialog=new Stage(StageStyle.UTILITY);
         dialog.initModality(Modality.WINDOW_MODAL);
         dialog.initOwner(btnOk.getScene().getWindow());
         
         Parent parent=FXMLLoader.load(getClass().getResource("/View/piechart.fxml"));
         PieChart pieChart=(PieChart)parent.lookup("#pieChart");
         Student studentPieChart=tableView.getSelectionModel().getSelectedItem(); //테이블뷰에서 선택된 행을 Student 객체로 연결시킴
         
         dialog.setTitle(studentPieChart.getName()+" 파이 그래프");
         ObservableList list=FXCollections.observableArrayList();
         list.add(new PieChart.Data("총점", Double.parseDouble(studentPieChart.getTotal())));
         list.add(new PieChart.Data("평균", Double.parseDouble(studentPieChart.getAvg())));
         pieChart.setData(list);
         
         Button btnClose=(Button)parent.lookup("#btnClose");
         btnClose.setOnAction(e-> dialog.close());
         
         Scene scene=new Scene(parent);
         dialog.setScene(scene);
         dialog.show();
      } catch (IOException e) {
      }
   }




   private void handlerBtnEditAction(ActionEvent event) {
//      System.out.println("ff");
      try {
      FXMLLoader loader=new FXMLLoader();
      loader.setLocation(getClass().getResource("/View/formedit.fxml"));
      
      Stage dialog=new Stage(StageStyle.UTILITY);
      dialog.initModality(Modality.WINDOW_MODAL);  //모달 대화상자를 만들겠다.
      dialog.initOwner(btnOk.getScene().getWindow());  //현재화면에 Cotroller를 선택하면 primaryStage를 준다.
      dialog.setTitle("수정내용");
      
      Parent parentEdit=loader.load();
      
      Student studentEdit=tableView.getSelectionModel().getSelectedItem();
      selectedIndex= tableView.getSelectionModel().getSelectedIndex();
      
      
      TextField editName=(TextField)parentEdit.lookup("#txtName");
      TextField editYear=(TextField)parentEdit.lookup("#txtYear");
      TextField editBan=(TextField)parentEdit.lookup("#txtBan");
      TextField editGender=(TextField)parentEdit.lookup("#txtGender");
      TextField editKorean=(TextField)parentEdit.lookup("#txtKorean");
      TextField editEnglish=(TextField)parentEdit.lookup("#txtEnglish");
      TextField editMath=(TextField)parentEdit.lookup("#txtMath");
      TextField editSic=(TextField)parentEdit.lookup("#txtSic");
      TextField editSoc=(TextField)parentEdit.lookup("#txtSoc");
      TextField editMusic=(TextField)parentEdit.lookup("#txtMusic");
      TextField editTotal=(TextField)parentEdit.lookup("#txtTotal");
      TextField editAvg=(TextField)parentEdit.lookup("#txtAvg");
      
      editName.setText(studentEdit.getName());
      editYear.setText(studentEdit.getLevel());
      editBan.setText(studentEdit.getBan());
      editGender.setText(studentEdit.getGender());
      editKorean.setText(studentEdit.getKorean());
      editEnglish.setText(studentEdit.getEnglish());
      editMath.setText(studentEdit.getMath());
      editSic.setText(studentEdit.getSic());
      editSoc.setText(studentEdit.getSoc());
      editMusic.setText(studentEdit.getSic());
      editTotal.setText(studentEdit.getTotal());
      editAvg.setText(studentEdit.getAvg());
      
      //이름, 학년, 반 성별 , 총합, 평균 수정을 하지못하도록 처리한다.
      
      editName.setDisable(true);
      editYear.setDisable(true);
      editBan.setDisable(true);
      editGender.setDisable(true);
      editTotal.setDisable(true);
      editAvg.setDisable(true);
      
      Button btnFormCal=(Button) parentEdit.lookup("#btnFormCal");
      Button btnFormAdd=(Button) parentEdit.lookup("#btnFormAdd");
      Button btnFormCancel=(Button) parentEdit.lookup("#btnFormCancel");
      
      btnFormAdd.setDisable(true);   //저장기능 비활성화-> 계산 후 활성화 시킬것
      btnFormCal.setOnAction(e->{
         int sum=Integer.parseInt(editKorean.getText())+Integer.parseInt(editEnglish.getText())+
         Integer.parseInt(editMath.getText())+Integer.parseInt(editSic.getText())+
         Integer.parseInt(editSoc.getText())+Integer.parseInt(editMusic.getText());
         double avg=sum/6.0;
         editTotal.setText(sum+"");
         editAvg.setText(avg+"");
         btnFormCal.setDisable(true);   // 계산버튼을 더이상 작동하지 않도록 설정
         btnFormAdd.setDisable(false);  //저장버튼을 활성화
         
      });
      
         btnFormAdd.setOnAction(e -> {
            TextField txtName=(TextField)parentEdit.lookup("#txtName");
            TextField txtYear=(TextField)parentEdit.lookup("#txtYear");
            TextField txtBan=(TextField)parentEdit.lookup("#txtBan");
            TextField txtGender=(TextField)parentEdit.lookup("#txtGender");
            TextField txtKorean=(TextField)parentEdit.lookup("#txtKorean");
            TextField txtEnglish=(TextField)parentEdit.lookup("#txtEnglish");
            TextField txtMath=(TextField)parentEdit.lookup("#txtMath");
            TextField txtSic=(TextField)parentEdit.lookup("#txtSic");
            TextField txtSoc=(TextField)parentEdit.lookup("#txtSoc");
            TextField txtMusic=(TextField)parentEdit.lookup("#txtMusic");
            TextField txtTotal=(TextField)parentEdit.lookup("#txtTotal");
            TextField txtAvg=(TextField)parentEdit.lookup("#txtAvg");
            
            data.remove(selectedIndex); //수정했던 기존의 내용을 없애버린다.
            data.add(new Student(txtName.getText(), txtYear.getText(), txtBan.getText(), 
                  txtGender.getText(), txtKorean.getText(), txtEnglish.getText(), 
                  txtMath.getText(), txtSic.getText(), txtSoc.getText(), txtMusic.getText(), 
                  txtTotal.getText(), txtAvg.getText()));
            dialog.close();
            
            btnDelete.setDisable(true);  //삭제버튼 비활성화
            btnEdit.setDisable(true);   //수정버튼 비활성화
         });
      
         
         btnFormCancel.setOnAction(e -> {
            btnDelete.setDisable(true);  //삭제버튼 비활성화
            btnEdit.setDisable(true);   //수정버튼 비활성화
            dialog.close();               
         });
      
         Scene scene=new Scene(parentEdit);
         dialog.setScene(scene);
         dialog.setResizable(false);
         dialog.show();
               
      } catch (IOException e) {
         
         
      }
      
      
   }
   
   
   
   private void handlerBtnDeleteAction(ActionEvent event) {
      Student student=data.remove(selectedIndex);
      
      if(student != null){
         Alert alert=new Alert(AlertType.WARNING);
         alert.setTitle("삭제진행 성공");
         alert.setHeaderText("삭제완료. 위치 : "+selectedIndex);
         alert.setContentText("삭제했습니다!"+student.toString());
         alert.showAndWait();
      }
      handlerBtnInitAction(event); //초기화를 부르면 된다.
      
      
   }




   private void handlerBtnExitAction(ActionEvent event) {
      Platform.exit();
   }

   private void handlerBtnOkAction(ActionEvent event) {
      String name=txtName.getText();
      String level=cbYear.getValue();
      String ban=txtBan.getText();
      String gender=(String) genderGroup.getSelectedToggle().getUserData();
      String korean=txtKo.getText();
      String english=txtEng.getText();
      String math=txtMath.getText();
      String sic=txtSic.getText();
      String soc=txtSoc.getText();
      String music=txtMusic.getText();
      String total=txtTotal.getText();
      String avg=txtAvg.getText();
      
      
      
      try{
         data.add(new Student(name,level,ban,gender,korean,english,math,sic,soc,music,total,avg));
      }catch(Exception e){
         
      }
   }


   private void handlerBtnInitAction(ActionEvent event) {
      txtName.setEditable(true); //텍스트 필드에 값을 클리어시킨다.
      cbYear.setDisable(false); //텍스트필드를 편집할수 있도록 진행한다.
      cbYear.getSelectionModel().clearSelection();
      txtName.clear();
      txtBan.clear();
      txtBan.setEditable(true);
      
      rbFemale.setDisable(false);
      rbMale.setDisable(false);
      genderGroup.selectToggle(null);   // 라디오버튼들을 아무것도 선택하지 않는다.
      
      txtKo.clear();
      txtKo.setEditable(true);
      txtEng.clear();
      txtEng.setEditable(true);
      txtMath.clear();
      txtMath.setEditable(true);
      txtSic.clear();
      txtSic.setEditable(true);
      txtSoc.clear();
      txtSoc.setEditable(true);
      txtMusic.clear();
      txtMusic.setEditable(true);
      txtTotal.clear();
      txtTotal.setEditable(true);
      txtAvg.clear();
      txtAvg.setEditable(true);
      
      
      btnTotal.setDisable(false);
      btnOk.setDisable(true);
      btnEdit.setDisable(false);
   }

   protected void handleBtnTotalAction(ActionEvent event) {
//      1.이름하고 반을 입력 안하면 경고를 주겠다.
      if(txtName.getText().equals("") || txtBan.getText().equals(toString())){
      Alert alert=new Alert(AlertType.WARNING);
      alert.setTitle("정보 입력");
      alert.setHeaderText("학생이름과 반을 입력하시오.");
      alert.setContentText("다음에는 주의하세요!");
      alert.showAndWait();
      }
      int korean=Integer.parseInt(txtKo.getText());
      int english=Integer.parseInt(txtEng.getText());
      int math=Integer.parseInt(txtMath.getText());
      int sic=Integer.parseInt(txtSic.getText());
      int soc=Integer.parseInt(txtSoc.getText());
      int music=Integer.parseInt(txtMusic.getText());
      int total;
//      만약에 6개 값중에 null값이 넘어오면 오류발생 NumberFormatException을 던져버린다. handle메서드에 작성함.
      total=korean+english+math+sic+soc+music; //합계 계산
      student.setTotal(total+"");
      
      student.setName(txtName.getText());
      student.setLevel(cbYear.getItems().toString());     //+""를 사용하여도 됨.
      student.setBan(txtBan.getText());
      student.setGender(genderGroup.getUserData()+"");   //toString()을 사용하여도됨.
      student.setKorean(txtKo.getText());
      student.setEnglish(txtEng.getText());
      student.setMath(txtMath.getText());
      student.setSic(txtSic.getText());
      student.setSoc(txtSoc.getText());
      student.setMusic(txtMusic.getText());
      
      txtTotal.setText(student.getTotal());
      
      
      btnAvg.setDisable(false);
      btnTotal.setDisable(true);      

      txtName.setEditable(false);
//      값이 이미 결정이 되어서 평균값이 구해졌으므로 입력된 데이타는 편집을 금지 시켜야한다.
      
      cbYear.setEditable(false);
      cbYear.setDisable(true);
      txtBan.setEditable(false);
      txtKo.setEditable(false);
      txtEng.setEditable(false);
      txtMath.setEditable(false);
      txtSic.setEditable(false);
      txtSoc.setEditable(false);
      txtMusic.setEditable(false);
//      data.get(0).getGender().equals("남성")
//      System.out.println(student.getGender());
      if(rbMale.isSelected()==true){
         rbMale.setSelected(true);
         rbFemale.setDisable(true);
      }else{
         rbFemale.setSelected(true);
         rbMale.setDisable(true);
      }
      
      
      
   }

   public void handleBtnAvgAction(ActionEvent event){
      Double avg=Integer.parseInt(student.getTotal()) / 6.0;
      txtAvg.setText(String.valueOf(avg));
      
      txtName.setEditable(false);
//      값이 이미 결정이 되어서 평균값이 구해졌으므로 입력된 데이타는 편집을 금지 시켜야한다.
      cbYear.getSelectionModel().clearSelection();
      txtBan.setEditable(false);
      txtKo.setEditable(false);
      txtEng.setEditable(false);
      txtMath.setEditable(false);
      txtSic.setEditable(false);
      txtSoc.setEditable(false);
      txtMusic.setEditable(false);
      
      btnAvg.setDisable(true);
      
      
      btnOk.setDisable(false);
      
      //확인 버튼 상태 설정
      if(editDelete == false){
         btnOk.setDisable(true);
      }
      
   }   
   
}