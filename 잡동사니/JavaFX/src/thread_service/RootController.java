package thread_service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RootController implements Initializable{
	@FXML private ProgressBar progressBar;
	@FXML private Label label;
	@FXML private Label lblWorkDone;
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Label lblResult;
	private TimeService timeservice;
	private int result;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnStart.setOnAction(event->handleBtnStart(event));
		btnStop.setOnAction(event->handleBtnStop(event));
		timeservice = new TimeService();
		timeservice.start();
		
	}
	public void handleBtnStart(ActionEvent event) {
		timeservice.restart();
		lblResult.setText("");
	}
	public void handleBtnStop(ActionEvent event) {
		timeservice.cancel();
	}
	
	class TimeService extends Service<Integer>{
		@Override
		protected Task<Integer> createTask() {
			Task<Integer> task = new Task<Integer>() {
				@Override
				protected Integer call() throws Exception {
				result=0;
				for(int i=0;i<=100;i++) {
					if(isCancelled()) {
						break;
					}
					result+=i;
					updateProgress(i, 100);
					updateMessage(String.valueOf(i));
					Thread.sleep(100);
					
				}
					return result;
				}
				
			};
			progressBar.progressProperty().bind(task.progressProperty());
			lblWorkDone.textProperty().bind(task.messageProperty());
			return task;
		}

		@Override
		protected void cancelled() {
			lblResult.setText(String.valueOf(result));
		}

		@Override
		protected void failed() {
			lblResult.setText("����");
		}

		@Override
		protected void succeeded() {
			lblWorkDone.textProperty().unbind();
			//62���� ���� task�� �޼����� ���������Ƿ� �װ��� Ǯ�����
			//succeeded���� �缳�������ϴ�
			lblWorkDone.setText("�Ϸ�ǵǵ�");
			lblResult.setText(String.valueOf(getValue()));
			
			//Service���� ���ϵǴ� ���� getValue�� �ް� �ȴ�
			//update�޼ҵ�� Task���ο��� �� �� �ִ� ���̹Ƿ� �ܺθ޼ҵ��� ���⿡���� �� �� ����.
		}	
	}
}