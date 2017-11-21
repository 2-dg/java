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
//						updateMessage("��ҵ�");
						break;
					}
					updateProgress(i, 100);
					updateMessage(String.valueOf(i));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
//						if(isCancelled()) {
//						updateMessage("��ҵ�");
//						break;
//						}
					}
			}
			return null;
			}//end of call

			@Override
			protected void succeeded() {//TASK�� �Ϸ�Ǹ� �ڵ����� Call back�Ǵ� �Լ�
				updateMessage("�޼ҵ尡 �Ϸ�� succeeded����");
			}

			@Override
			protected void cancelled() {
				updateMessage("�޼ҵ尡 ��ҵ� cancelled����");
			}

			@Override
			protected void failed() {
				updateMessage("���ͷ�Ʈ(����)�� �Ͼ");
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
//					//�̰� �۵��� �� ��. �� �޼ҵ�δ� UI�� ������ �� ���� ����.
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