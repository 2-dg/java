package basic;

import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application{
	public AppMain() {
		System.out.println(Thread.currentThread().getName()+" : AppMain() 생성자 호출");
	}	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+" : main() 호출");
		
		
		
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName()+" : init() 호출");
//		Parameters params = getParameters();
//		List<String> list = params.getRaw();
//		Map<String,String> map = params.getNamed();
//		System.out.println(list.toString());
//		System.out.println(map.size());
		super.init();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName()+" : start() 호출");		
		primaryStage.setTitle("ㅎㅎㅎㅎ");
		primaryStage.show();	
	}
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName()+" : stop() 호출");		
		super.stop();
	}
	
	
}