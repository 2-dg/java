package demon_and_independent_threads;

public class MyThread implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 21; i++) {
			try {
				System.out.println(i);
				Thread.sleep(501);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("MyThread종료");
		String name = Thread.currentThread().getName();
		System.out.println("스레드 이름 출력 : "+name);
	}
}