package five_ways_of_threads;

import java.awt.Toolkit;

public class ThreadTestMain {
	public static Toolkit tool = Toolkit.getDefaultToolkit();

	public static void main(String[] args) throws InterruptedException {
		ThreadA ta = new ThreadA();
		Runnable tb = new ThreadB();
		Thread tb2 = new Thread(tb);
		Thread tb22 = new Thread(new ThreadB()); // 식으로도 됨(러너블 인터페이스 방법)
		(new ThreadA()).start(); // 도 됨
		Thread tc = new Thread(new Runnable() { // 러너블 임시객체
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("띵3");
				}
			}
		});
		Thread td = new Thread(() -> { // 러너블 임시객체 람다식
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("띵4");
			}
		});
		Thread te = new Thread() { // 스레드 임시객체식
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("띵5");
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
			Thread.sleep(750); // 프로그래머가 스케쥴러를 제어하는 것임.
								// 스레드를 750초동안 일시정지상태(블락상태)에 넣겠다는 것
			System.out.println("beep");
		}

	}
}