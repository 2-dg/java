package media_control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class RootController implements Initializable{
	@FXML private MediaView mediaView;
	@FXML private Button btnPlay;
	@FXML private Button btnPause;
	@FXML private Button btnStop;
	@FXML private ProgressBar progressBar;
	@FXML private ProgressIndicator progressIndicator;
	@FXML private Label labelTime;
	@FXML private Slider sliderVolume;
	@FXML private ImageView imageView;
	private MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPlay.setOnAction(event-> handPlayAction(event));
		btnPause.setOnAction(event-> handPauseAction(event));
		btnStop.setOnAction(event-> handStopAction(event));
		
//		Media media = new Media(getClass().getResource("media/audio.wav").toString());
		Media media = new Media(getClass().getResource("media/video.m4v").toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setVolume(0.5);
		sliderVolume.setValue(50);
		
		//임시객체이면서 스레드로 구성딤
		mediaPlayer.setOnReady(()-> {
				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
					@Override
					public void changed(ObservableValue<? extends Duration> observable,
							Duration oldValue, Duration newValue) {
						double progress = mediaPlayer.getCurrentTime().toSeconds()/
								mediaPlayer.getTotalDuration().toSeconds();
						progressBar.setProgress(progress);
						progressIndicator.setProgress(progress);
						labelTime.setText(
						(int)mediaPlayer.getCurrentTime().toSeconds()+"/"+
						(int)mediaPlayer.getTotalDuration().toSeconds()+"sec");
					}
				});
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);
				if(mediaPlayer.isAutoPlay()) {
					imageView.setVisible(false);
				}else {
					imageView.setVisible(true);
				}
			});
		mediaPlayer.setOnPlaying(()-> {
				btnPlay.setDisable(true);
				btnPause.setDisable(false);
				btnStop.setDisable(false);});
		mediaPlayer.setOnPaused(()-> {
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(false);});
		mediaPlayer.setOnStopped(()-> {
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);});
		mediaPlayer.setOnEndOfMedia(()-> {
				progressBar.setProgress(1.0);
				progressIndicator.setProgress(1.0);
				mediaPlayer.stop();
				//mediaPlayer.seek(mediaPlayer.getStartTime());
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);});
		sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				mediaPlayer.setVolume(sliderVolume.getValue()/100.0);
			}
		});
	}//end of initialize
	private void handStopAction(ActionEvent event) {
		mediaPlayer.stop();
	}
	private void handPauseAction(ActionEvent event) {
		mediaPlayer.pause();
	}
	protected void handPlayAction(ActionEvent event) {
		mediaPlayer.play();
	}
}