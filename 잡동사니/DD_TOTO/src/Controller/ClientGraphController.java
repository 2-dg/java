package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.GameVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class ClientGraphController implements Initializable{
	
	@FXML private PieChart pieCG;
	@FXML private Label labelCGwinRate;
	@FXML private Label labelCGwinMoney;
	@FXML private Label labelCGdrawMoney;
	@FXML private Label labelCGdrawRate;
	@FXML private Label labelCGloseRate;
	@FXML private Label labelCGloseMoney;
	
	private GameVO game = ClientController.getSelectedGame();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<PieChart.Data> pList = FXCollections.observableArrayList();
		pList.add(new PieChart.Data("½Â¸®", game.getWinMoney()));
		pList.add(new PieChart.Data("¹«½ÂºÎ", game.getDrawMoney()));
		pList.add(new PieChart.Data("ÆÐ¹è", game.getLoseMoney()));
		pieCG.setData(pList);
		
		labelCGwinRate.setText(String.valueOf(game.getWinRate()));
		labelCGwinMoney.setText(String.valueOf(game.getWinMoney())+"¿ø");
		labelCGdrawRate.setText(String.valueOf(game.getDrawRate()));
		labelCGdrawMoney.setText(String.valueOf(game.getDrawMoney())+"¿ø");
		labelCGloseRate.setText(String.valueOf(game.getLoseRate()));
		labelCGloseMoney.setText(String.valueOf(game.getLoseMoney())+"¿ø");
		
	}
}