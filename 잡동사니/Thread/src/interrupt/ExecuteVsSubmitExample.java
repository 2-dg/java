package interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteVsSubmitExample {

	public static void main(String[] args) {

		ExecutorService excutorService = Executors.newFixedThreadPool(4);	//������Ǯ ��������
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						String threadName = Thread.currentThread().getName();
						System.out.println(threadName + " : ���� �����忡�� �۾��ϰ� �ֽ��ϴ�.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			//Thread tread = new Thread(runnable);������ �ϸ� �ӵ��� ���� ������ ���������� �ʴ�.
			
			excutorService.execute(runnable);
		}
	}
}
