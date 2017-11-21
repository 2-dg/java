package interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteVsSubmitExample {

	public static void main(String[] args) {

		ExecutorService excutorService = Executors.newFixedThreadPool(4);	//스레드풀 갯수설정
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						String threadName = Thread.currentThread().getName();
						System.out.println(threadName + " : 현재 스레드에서 작업하고 있습니다.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			//Thread tread = new Thread(runnable);식으로 하면 속도는 빠를 지언정 안정적이지 않다.
			
			excutorService.execute(runnable);
		}
	}
}
