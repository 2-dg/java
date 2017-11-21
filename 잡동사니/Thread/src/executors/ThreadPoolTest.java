package executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		final int number = 10;					//이걸 안에서 연산하는 데 쓰려면 final로 선언해야 함
												//왜냐면 main이 끝나면 지역변수인 number는 없어지는데
												//스레드는 독립적으로 실행되고 있기 때문에 오류가 되는 거임
												//데몬스레드라 하더라도 메인과는 다른 작업이므로(그냥 종료시점만 같고)
												//쓸 수 없다
		Runnable r1 = new ThreadA();
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i=10;i>=0;i--) {
					try {
						System.out.println("Thread B실행 : "+i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Runnable r3 =() -> {
				for(int i=10;i>=0;i--) {
					try {
						System.out.println("Thread C실행 : "+i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		};
		Runnable r4 =() -> {
			for(int i=10;i>=0;i--) {
				try {
					System.out.println("Thread D실행 : "+i+"  제네릭테스트  "+number);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread rr1 = new Thread(r1);
		Thread rr2 = new Thread(r2);
		Thread rr3 = new Thread(r3);
		Thread rr4 = new Thread(r4);
//		rr1.start();
//		rr2.start();
//		rr3.start();
//		rr4.start();
		Executor exe = Executors.newCachedThreadPool();
		exe.execute(rr1);
		exe.execute(rr2);
		exe.execute(rr3);
		exe.execute(rr4);
	}
}