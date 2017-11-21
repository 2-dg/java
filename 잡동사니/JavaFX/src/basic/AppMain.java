package basic;

import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application{
	public AppMain() {
		System.out.println(Thread.currentThread().getName()+" : AppMain() ������ ȣ��");
	}	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+" : main() ȣ��");
		
		
		
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName()+" : init() ȣ��");
//		Parameters params = getParameters();
//		List<String> list = params.getRaw();
//		Map<String,String> map = params.getNamed();
//		System.out.println(list.toString());
//		System.out.println(map.size());
		super.init();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName()+" : start() ȣ��");		
		primaryStage.setTitle("��������");
		primaryStage.show();	
	}
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName()+" : stop() ȣ��");		
		super.stop();
	}
	
	
}