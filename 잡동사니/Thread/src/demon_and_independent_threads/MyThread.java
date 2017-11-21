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
		System.out.println("MyThread����");
		String name = Thread.currentThread().getName();
		System.out.println("������ �̸� ��� : "+name);
	}
}