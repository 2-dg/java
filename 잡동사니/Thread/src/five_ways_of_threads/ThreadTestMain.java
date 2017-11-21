package five_ways_of_threads;

import java.awt.Toolkit;

public class ThreadTestMain {
	public static Toolkit tool = Toolkit.getDefaultToolkit();

	public static void main(String[] args) throws InterruptedException {
		ThreadA ta = new ThreadA();
		Runnable tb = new ThreadB();
		Thread tb2 = new Thread(tb);
		Thread tb22 = new Thread(new ThreadB()); // �����ε� ��(���ʺ� �������̽� ���)
		(new ThreadA()).start(); // �� ��
		Thread tc = new Thread(new Runnable() { // ���ʺ� �ӽð�ü
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("��3");
				}
			}
		});
		Thread td = new Thread(() -> { // ���ʺ� �ӽð�ü ���ٽ�
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("��4");
			}
		});
		Thread te = new Thread() { // ������ �ӽð�ü��
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("��5");
				}
			}
		};
		ta.start();
		tb2.start();
		tc.start();
		td.start();
		te.start();
		for (int i = 0; i < 5; i++) {
			tool.beep();
			Thread.sleep(750); // ���α׷��Ӱ� �����췯�� �����ϴ� ����.
								// �����带 750�ʵ��� �Ͻ���������(�������)�� �ְڴٴ� ��
			System.out.println("beep");
		}

	}
}