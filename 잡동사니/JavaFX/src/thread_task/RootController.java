package thread_task;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
	private Task<Void> task;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnStart.setOnAction(event->handleBtnStart(event));
		btnStop.setOnAction(event->handleBtnStop(event));
	}

	public void handleBtnStart(ActionEvent event) {
		task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {				
				for(int i=0;i<=100;i++) {
					if(isCancelled()) {
//						updateMessage("취소됨");
						break;
					}
					updateProgress(i, 100);
					updateMessage(String.valueOf(i));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
//						if(isCancelled()) {
//						updateMessage("취소됨");
//						break;
//						}
					}
			}
			return null;
			}//end of call

			@Override
			protected void succeeded() {//TASK가 완료되면 자동으로 Call back되는 함수
				updateMessage("메소드가 완료돼 succeeded실행");
			}

			@Override
			protected void cancelled() {
				updateMessage("메소드가 취소돼 cancelled실행");
			}

			@Override
			protected void failed() {
				updateMessage("인터럽트(예외)가 일어남");
			}
			
			
		};//End of Task
		
		
//		task = new Task<Void>() {
//			@Override
//			protected Void call() throws Exception {
//				for(int i=0;i<=100;i++) {
//					if(isCancelled()) {
//						break;
//					}
//					progressBar.setProgress((double)i/100);
////					lblWorkDone.setText(String.valueOf(i));
//					//이게 작동이 안 됨. 이 메소드로는 UI를 수정할 수 없게 만듬.
//					final int fi=i;
//					Platform.runLater(()-> lblWorkDone.setText(String.valueOf(fi)));
//					Thread.sleep(200);	
//				}		
//				return null;
//			}
//		};
		
		progressBar.progressProperty().bind(task.progressProperty());
		lblWorkDone.textProperty().bind(task.messageProperty());
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();	
	}
	public void handleBtnStop(ActionEvent event) {
		task.cancel();
	}
}