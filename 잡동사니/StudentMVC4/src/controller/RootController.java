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
//ȭ�� ID����=====================================================
   @FXML TableView<Student> tableView=new TableView<>();   //���̺�� ���
   @FXML TextField txtName;      //����
   @FXML ComboBox<String> cbYear;    //�г�     
   @FXML TextField txtBan;               // ��
   @FXML ToggleGroup genderGroup;       //����,����  ����
   @FXML RadioButton rbMale;             //���� ��ư
   @FXML RadioButton rbFemale;          //���� ��ư
   
   @FXML TextField txtTotal;  //����
   @FXML Button btnAvg;     //���
   @FXML Button btnInit;      //�ʱ�ȭ
   @FXML Button btnOk;      //Ȯ��
   @FXML Button btnExit;     //���
   @FXML Button btnEdit;     //����
   @FXML Button btnDelete;  //����
   
   @FXML TextField txtKo;          //����
   @FXML TextField txtEng;         //����
   @FXML TextField txtMath;        //����
   @FXML TextField txtSic;           //����
   @FXML TextField txtSoc;                  
   @FXML TextField txtMusic;        //����
   @FXML Button btnTotal;          //����
   @FXML TextField txtAvg;          //���
   @FXML TextField txtSearch;       //�˻�
   
   @FXML Button btnSearch;         //�˻� ��ư
   
   @FXML Button btnBarChart;   //����� �׷���
   @FXML DatePicker dbDate;  //��¥
//=============================================================

//���̺� ����� ����Ÿ���� ����=======================================
   Student student=new Student();  //����Ÿ ������ ��ü����.
   ObservableList<Student> data=FXCollections.observableArrayList();  //���̺�信 ������ ArrayList
   ObservableList<Student> selectStudent;
   
   
//=============================================================
   
   //Ȯ�� ��ư ���� ����
   boolean editDelete=true;

   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
//       ��ư �۵����� ���==============================================
      txtTotal.setEditable(false);   //�����Է¶��� ��������
      txtAvg.setEditable(false); //����Է¶��� ��������
      
      btnAvg.setDisable(true);   //��չ�ư �۵�����
      btnOk.setDisable(true);   //Ȯ�ι�ư �۵�����
      btnEdit.setDisable(true);   //������ư �۵�����
      btnDelete.setDisable(true);   //������ư �۵�����
//==============================================================
//���� �Է¸� �����ϰ� �ϴ� ��. ������ �� ��� �ؽ�Ʈ�ʵ忡 �����ؾ� ��.
      DecimalFormat format = new DecimalFormat("###");//���ڸ� 3�ڸ� �ްڴٴ� ��.
      txtKo.setTextFormatter(new TextFormatter<>(event -> {//�ؽ�Ʈ�ʵ忡 ������ �����ϰڴ�
    	  if(event.getControlNewText().isEmpty()) { //�Է��� ���� ���� �� �����ϴ� ���ǹ�.
    		  return event;							//getControlNewText�� �Է¹޴� �ؽ�Ʈ�� �ǹ�
    	  }
    	  ParsePosition parsePosition = new ParsePosition(0);//��ġ����
    	  //���ڿ��� �Է��� ���� "###"3�ڸ� ������������ ��ȯ��. ��) a12 -> 3�ڸ� ������������ �ٲ� �� ������ null�� ��.
    	  Object object = format.parse(event.getControlNewText(),parsePosition);
    	  //�Էµ� ���� format����("###")�� �°� �ٲ�. ��) 345678�� ������ 0�ڸ����� 3�ڸ���, 345�� ������.
    	  //���࿡ a123�� ���´ٸ� a12�� �ν��ϴµ� a�� �������Ƿ� null�� ��ȯ. ���ʿ� �۾��� �Ƚ���.
    	  //��ȯ�� ���� null.���ڰ� �ƴ� ���ڰ� ������ null�� �����ؼ� �ش�� �ؽ�Ʈ�ʵ忡 �ش� ���ڸ� �Է½�Ű�� ����
    	  //�̰ų� ��ȯ�� ��ġ�� ���̰� ���� ���̺��� ���ų� ���� �Է��� ���̰� 4�̸� null �ƴϸ� �ش�� ������ ������
    	  if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
    			  ||event.getControlNewText().length()==4) {
    		  return null;
    	  }else {
    		  return event;
    	  }    	  
      }));
      
          
      
      
      
      
      
      //1. �޺��ڽ��� �г��� �����ؾ��Ѵ�.
//      ObservableList<String> list=FXCollections.observableArrayList();
//      list.add(new String("1"));
//      list.add(new String("2"));
//      list.add(new String("3"));
//      list.add(new String("4"));
//      list.add(new String("5"));
//      list.add(new String("6"));
      
//      �г� �޺��ڽ� ����Ÿ �Է�ó������      
      cbYear.setItems(FXCollections.observableArrayList("1","2","3","4","5","6"));
      
//      ���̺�� ����Ÿ �Է�ó�� ����
      tableView.setEditable(false);
   
//      ���̺� �÷����� �� �ش�� �÷��� ������� ��Ÿ��(�������), �ش�Ŭ������ ��Ī��Ų��.
      TableColumn colName=new TableColumn("����");
      colName.setMaxWidth(70);
      colName.setStyle("-fx-allignment:CENTER");
      colName.setCellValueFactory(new PropertyValueFactory<>("name"));
      
      TableColumn colLevel=new TableColumn("�г�");
      colLevel.setMaxWidth(40);
      colLevel.setStyle("-fx-allignment:CENTER");
      colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
      
      TableColumn colBan=new TableColumn("��");
      colBan.setMaxWidth(40);
      colBan.setStyle("-fx-allignment:CENTER");
      colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));
      
      TableColumn colGender=new TableColumn("����");
      colGender.setMaxWidth(40);
      colGender.setStyle("-fx-allignment:CENTER");
      colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      
      TableColumn colKorean=new TableColumn("����");
      colKorean.setMaxWidth(40);
      colKorean.setStyle("-fx-allignment:CENTER");
      colKorean.setCellValueFactory(new PropertyValueFactory<>("korean"));
      
      TableColumn colEnglish=new TableColumn("����");
      colEnglish.setMaxWidth(40);
      colEnglish.setStyle("-fx-allignment:CENTER");
      colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
      
      TableColumn colMath=new TableColumn("����");
      colMath.setMaxWidth(40);
      colMath.setStyle("-fx-allignment:CENTER");
      colMath.setCellValueFactory(new PropertyValueFactory<>("math"));
      
      TableColumn colSic=new TableColumn("����");
      colSic.setMaxWidth(40);
      colSic.setStyle("-fx-allignment:CENTER");
      colSic.setCellValueFactory(new PropertyValueFactory<>("sic"));
      
      TableColumn colSoc=new TableColumn("��ȸ");
      colSoc.setMaxWidth(40);
      colSoc.setStyle("-fx-allignment:CENTER");
      colSoc.setCellValueFactory(new PropertyValueFactory<>("soc"));
      
      TableColumn colMusic=new TableColumn("����");
      colMusic.setMaxWidth(40);
      colMusic.setStyle("-fx-allignment:CENTER");
      colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));
      
      TableColumn colTotal=new TableColumn("����");
      colTotal.setMaxWidth(40);
      colTotal.setStyle("-fx-allignment:CENTER");
      colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
      
      TableColumn colAvg=new TableColumn("���");
      colAvg.setMaxWidth(50);
      colAvg.setStyle("-fx-allignment:CENTER");
      colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
      
      tableView.setItems(data);
      tableView.getColumns().addAll(colName, colLevel, colBan, colGender, colKorean, 
            colEnglish, colMath, colSic, colSoc, colMusic, colTotal, colAvg);
      
//      ������ư�� �������� �۵��ϴ� �̺�Ʈ
      btnTotal.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            try{
               handleBtnTotalAction(event);
            }catch(NumberFormatException e){   //������ �ϴ� ���߿� null���� ����� <-�̿����� �߻��Ѵ�.
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("���� �Է�");
               alert.setHeaderText("������ �Է��Ͻÿ�.");
               alert.setContentText("�������� �����ϼ���!");
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
//            System.out.println("�� ��ȣ : "+selectedIndex);
            txtName.setText(selectStudent.get(0).getName());
            cbYear.setValue(selectStudent.get(0).getLevel());
            
            
         }
      });
      
      btnOk.setOnAction(event-> {
//            if(btnEdit.getOnAction().equals(false)){
//               System.out.println("������ư ����");
//            }
            if(txtName.getText().equals(toString()) || txtBan.getText().equals(toString())){
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("���� �Է�");
               alert.setHeaderText("�л��� �̸��� ���� �Է��Ͻÿ�.");
               alert.setContentText("�������� �����ϼ���!");
               alert.showAndWait();
            }
            
            try{
            data.add(new Student(txtName.getText(), cbYear.getSelectionModel().getSelectedItem(), 
                  txtBan.getText(), genderGroup.getSelectedToggle().getUserData().toString(), 
                  txtKo.getText(), txtEng.getText(), txtMath.getText(), txtSic.getText(), txtSoc.getText(), 
                  txtMusic.getText(), txtTotal.getText(), txtAvg.getText()));
            }catch(Exception e){
               Alert alert=new Alert(AlertType.WARNING);
               alert.setTitle("���� �Է�");
               alert.setHeaderText("�л��� ������ �Է��Ͻÿ�.");
               alert.setContentText("�������� �����ϼ���!");
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
	   dialog.setTitle("���� �׷���");
	   try {
		Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));
		BarChart barChart = (BarChart) parent.lookup("#barChart");

		XYChart.Series seriesKorean = new XYChart.Series();
		seriesKorean.setName("����");
		ObservableList koreanList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			koreanList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getKorean())));
		}
		seriesKorean.setData(koreanList);
		barChart.getData().add(seriesKorean);
		
		XYChart.Series seriesMath = new XYChart.Series();
		seriesMath.setName("����");
		ObservableList mathList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			mathList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getMath())));
		}
		seriesMath.setData(mathList);
		barChart.getData().add(seriesMath);
		
		XYChart.Series seriesEnglish = new XYChart.Series();
		seriesEnglish.setName("����");
		ObservableList englishList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			englishList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getEnglish())));
		}
		seriesEnglish.setData(englishList);
		barChart.getData().add(seriesEnglish);
		
		XYChart.Series seriessic = new XYChart.Series();
		seriessic.setName("����");
		ObservableList sicList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			sicList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getSic())));
		}
		seriessic.setData(sicList);
		barChart.getData().add(seriessic);
		
		XYChart.Series seriessoc = new XYChart.Series();
		seriessoc.setName("��ȸ");
		ObservableList socList = FXCollections.observableArrayList();
		for(int i=0;i<data.size();i++) {
			socList.add(new XYChart.Data(data.get(i).getName(),Integer.parseInt(data.get(i).getSoc())));
		}
		seriessoc.setData(socList);
		barChart.getData().add(seriessoc);
		
		XYChart.Series seriesMusic = new XYChart.Series();
		seriesMusic.setName("����");
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
      selectStudent=tableView.getSelectionModel().getSelectedItems();  //���õ� ��ü
      selectedIndex=tableView.getSelectionModel().getSelectedIndex();  //���õ� ��ġ
      if(event.getClickCount() != 2){  //Ŭ���� 2�� ������?
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
         Student studentPieChart=tableView.getSelectionModel().getSelectedItem(); //���̺�信�� ���õ� ���� Student ��ü�� �����Ŵ
         
         dialog.setTitle(studentPieChart.getName()+" ���� �׷���");
         ObservableList list=FXCollections.observableArrayList();
         list.add(new PieChart.Data("����", Double.parseDouble(studentPieChart.getTotal())));
         list.add(new PieChart.Data("���", Double.parseDouble(studentPieChart.getAvg())));
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
      dialog.initModality(Modality.WINDOW_MODAL);  //��� ��ȭ���ڸ� ����ڴ�.
      dialog.initOwner(btnOk.getScene().getWindow());  //����ȭ�鿡 Cotroller�� �����ϸ� primaryStage�� �ش�.
      dialog.setTitle("��������");
      
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
      
      //�̸�, �г�, �� ���� , ����, ��� ������ �������ϵ��� ó���Ѵ�.
      
      editName.setDisable(true);
      editYear.setDisable(true);
      editBan.setDisable(true);
      editGender.setDisable(true);
      editTotal.setDisable(true);
      editAvg.setDisable(true);
      
      Button btnFormCal=(Button) parentEdit.lookup("#btnFormCal");
      Button btnFormAdd=(Button) parentEdit.lookup("#btnFormAdd");
      Button btnFormCancel=(Button) parentEdit.lookup("#btnFormCancel");
      
      btnFormAdd.setDisable(true);   //������ ��Ȱ��ȭ-> ��� �� Ȱ��ȭ ��ų��
      btnFormCal.setOnAction(e->{
         int sum=Integer.parseInt(editKorean.getText())+Integer.parseInt(editEnglish.getText())+
         Integer.parseInt(editMath.getText())+Integer.parseInt(editSic.getText())+
         Integer.parseInt(editSoc.getText())+Integer.parseInt(editMusic.getText());
         double avg=sum/6.0;
         editTotal.setText(sum+"");
         editAvg.setText(avg+"");
         btnFormCal.setDisable(true);   // ����ư�� ���̻� �۵����� �ʵ��� ����
         btnFormAdd.setDisable(false);  //�����ư�� Ȱ��ȭ
         
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
            
            data.remove(selectedIndex); //�����ߴ� ������ ������ ���ֹ�����.
            data.add(new Student(txtName.getText(), txtYear.getText(), txtBan.getText(), 
                  txtGender.getText(), txtKorean.getText(), txtEnglish.getText(), 
                  txtMath.getText(), txtSic.getText(), txtSoc.getText(), txtMusic.getText(), 
                  txtTotal.getText(), txtAvg.getText()));
            dialog.close();
            
            btnDelete.setDisable(true);  //������ư ��Ȱ��ȭ
            btnEdit.setDisable(true);   //������ư ��Ȱ��ȭ
         });
      
         
         btnFormCancel.setOnAction(e -> {
            btnDelete.setDisable(true);  //������ư ��Ȱ��ȭ
            btnEdit.setDisable(true);   //������ư ��Ȱ��ȭ
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
         alert.setTitle("�������� ����");
         alert.setHeaderText("�����Ϸ�. ��ġ : "+selectedIndex);
         alert.setContentText("�����߽��ϴ�!"+student.toString());
         alert.showAndWait();
      }
      handlerBtnInitAction(event); //�ʱ�ȭ�� �θ��� �ȴ�.
      
      
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
      txtName.setEditable(true); //�ؽ�Ʈ �ʵ忡 ���� Ŭ�����Ų��.
      cbYear.setDisable(false); //�ؽ�Ʈ�ʵ带 �����Ҽ� �ֵ��� �����Ѵ�.
      cbYear.getSelectionModel().clearSelection();
      txtName.clear();
      txtBan.clear();
      txtBan.setEditable(true);
      
      rbFemale.setDisable(false);
      rbMale.setDisable(false);
      genderGroup.selectToggle(null);   // ������ư���� �ƹ��͵� �������� �ʴ´�.
      
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
//      1.�̸��ϰ� ���� �Է� ���ϸ� ��� �ְڴ�.
      if(txtName.getText().equals("") || txtBan.getText().equals(toString())){
      Alert alert=new Alert(AlertType.WARNING);
      alert.setTitle("���� �Է�");
      alert.setHeaderText("�л��̸��� ���� �Է��Ͻÿ�.");
      alert.setContentText("�������� �����ϼ���!");
      alert.showAndWait();
      }
      int korean=Integer.parseInt(txtKo.getText());
      int english=Integer.parseInt(txtEng.getText());
      int math=Integer.parseInt(txtMath.getText());
      int sic=Integer.parseInt(txtSic.getText());
      int soc=Integer.parseInt(txtSoc.getText());
      int music=Integer.parseInt(txtMusic.getText());
      int total;
//      ���࿡ 6�� ���߿� null���� �Ѿ���� �����߻� NumberFormatException�� ����������. handle�޼��忡 �ۼ���.
      total=korean+english+math+sic+soc+music; //�հ� ���
      student.setTotal(total+"");
      
      student.setName(txtName.getText());
      student.setLevel(cbYear.getItems().toString());     //+""�� ����Ͽ��� ��.
      student.setBan(txtBan.getText());
      student.setGender(genderGroup.getUserData()+"");   //toString()�� ����Ͽ�����.
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
//      ���� �̹� ������ �Ǿ ��հ��� ���������Ƿ� �Էµ� ����Ÿ�� ������ ���� ���Ѿ��Ѵ�.
      
      cbYear.setEditable(false);
      cbYear.setDisable(true);
      txtBan.setEditable(false);
      txtKo.setEditable(false);
      txtEng.setEditable(false);
      txtMath.setEditable(false);
      txtSic.setEditable(false);
      txtSoc.setEditable(false);
      txtMusic.setEditable(false);
//      data.get(0).getGender().equals("����")
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
//      ���� �̹� ������ �Ǿ ��հ��� ���������Ƿ� �Էµ� ����Ÿ�� ������ ���� ���Ѿ��Ѵ�.
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
      
      //Ȯ�� ��ư ���� ����
      if(editDelete == false){
         btnOk.setDisable(true);
      }
      
   }   
   
}