package chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class RootController implements Initializable{
	@FXML private PieChart pieChart;
	@FXML private BarChart barChart;
	@FXML private AreaChart areaChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<PieChart.Data> plist = FXCollections.observableArrayList();
		//���׸� ���� ����
		plist.add(new PieChart.Data("AWT", 10.0));
		plist.add(new PieChart.Data("Swing", 30.0));
		plist.add(new PieChart.Data("SWT", 25.0));
		plist.add(new PieChart.Data("JavaFX", 35.0));
		//100�� ������ �ʾƵ� �˾Ƽ� ������ �����ؼ� ��Ÿ������.
		//���� ���� ������� �ƴ϶�� ���� ���� ����ؼ� ��ġ�� ��Ÿ���� �� ���� ��.
		pieChart.setData(plist);
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("����");
		ObservableList<XYChart.Data> s1list = FXCollections.observableArrayList();
		s1list.add(new XYChart.Data("2015",70.0));
		s1list.add(new XYChart.Data("2016",40.0));
		s1list.add(new XYChart.Data("2017",50.0));
		s1list.add(new XYChart.Data("2018",30.0));
		series1.setData(s1list);
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("����");
		ObservableList<XYChart.Data> s2list = FXCollections.observableArrayList();
		s2list.add(new XYChart.Data("2015",30));
		s2list.add(new XYChart.Data("2016",60));
		s2list.add(new XYChart.Data("2017",50));
		s2list.add(new XYChart.Data("2018",60));
		series2.setData(s2list);
		barChart.getData().add(series1);
		barChart.getData().add(series2);
		
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("��տµ�");
		series3.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",13),
				new XYChart.Data("2016",6),
				new XYChart.Data("2017",22),
				new XYChart.Data("2018",19)
				));
		
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("�׽�Ʈ");
		series4.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",19),
				new XYChart.Data("2016",15),
				new XYChart.Data("2017",4),
				new XYChart.Data("2018",30)
				));
		areaChart.getData().add(series3);
		areaChart.getData().add(series4);	
	}
}