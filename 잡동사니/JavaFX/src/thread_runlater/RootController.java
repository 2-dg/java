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
//			//이 반복문으로 실행하면 JavaApplicationThread가 while문의 반복에만 리소스가 할당되므로
//			//UI를 변경할 리소스적 여유가 없다. 그래서 프로그램이 멈춰버린다.
//		}		
		
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(!stop) {
					String srtTime = sdf.format(new Date());					
					//lblTime.setText(srtTime);
					//보통 Thread는 UI를 변경할 수 없게 설계해놨기 때문에 이렇게 쓰면 여기에서 문제가 발생한다.
					//1초에 한 번씩 UI변경이 안 된다는 오류가 발생
					Platform.runLater(()->lblTime.setText(srtTime));
					//이렇게 Platform.runLator안에 new Runnable로 스레드 재정의해줘야 정상적으로 UI를 변경한다.
					try {Thread.sleep(100);} catch (InterruptedException e) {}
					//이렇게 해야만 JavaApplicationThread는 UI의 유지에 리소스를 쓰고
					//새로 정의된 Thread가 Label값을 변경해서 리턴해서 UI가 변경되는 것이다.
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