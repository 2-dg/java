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
			lblResult.setText("실패");
		}

		@Override
		protected void succeeded() {
			lblWorkDone.textProperty().unbind();
			//62행의 라벨이 task의 메세지와 묶여있으므로 그것을 풀어줘야
			//succeeded에서 재설정가능하다
			lblWorkDone.setText("완료되되됨");
			lblResult.setText(String.valueOf(getValue()));
			
			//Service에서 리턴되는 값을 getValue로 받게 된다
			//update메소드는 Task내부에서 쓸 수 있는 것이므로 외부메소드인 여기에서는 쓸 수 없다.
		}	
	}
}