package thread_runlater;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootController implements Initializable{
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Label lblTime;
	private boolean stop;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnStart.setOnAction((event)-> handleBtnStart(event));
		btnStop.setOnAction((event)-> handleBtnStop(event));
		
	}
	protected void handleBtnStart(ActionEvent event) {
		stop = false;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		while(!stop) {
//			String srtTime = sdf.format(new Date());
//			lblTime.setText(srtTime);
//			//�� �ݺ������� �����ϸ� JavaApplicationThread�� while���� �ݺ����� ���ҽ��� �Ҵ�ǹǷ�
//			//UI�� ������ ���ҽ��� ������ ����. �׷��� ���α׷��� ���������.
//		}		
		
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(!stop) {
					String srtTime = sdf.format(new Date());					
					//lblTime.setText(srtTime);
					//���� Thread�� UI�� ������ �� ���� �����س��� ������ �̷��� ���� ���⿡�� ������ �߻��Ѵ�.
					//1�ʿ� �� ���� UI������ �� �ȴٴ� ������ �߻�
					Platform.runLater(()->lblTime.setText(srtTime));
					//�̷��� Platform.runLator�ȿ� new Runnable�� ������ ����������� ���������� UI�� �����Ѵ�.
					try {Thread.sleep(100);} catch (InterruptedException e) {}
					//�̷��� �ؾ߸� JavaApplicationThread�� UI�� ������ ���ҽ��� ����
					//���� ���ǵ� Thread�� Label���� �����ؼ� �����ؼ� UI�� ����Ǵ� ���̴�.
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	protected void handleBtnStop(ActionEvent event) {
		stop = true;
	}	
}