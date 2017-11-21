package executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		final int number = 10;					//�̰� �ȿ��� �����ϴ� �� ������ final�� �����ؾ� ��
												//�ֳĸ� main�� ������ ���������� number�� �������µ�
												//������� ���������� ����ǰ� �ֱ� ������ ������ �Ǵ� ����
												//���󽺷���� �ϴ��� ���ΰ��� �ٸ� �۾��̹Ƿ�(�׳� ��������� ����)
												//�� �� ����
		Runnable r1 = new ThreadA();
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i=10;i>=0;i--) {
					try {
						System.out.println("Thread B���� : "+i);
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
						System.out.println("Thread C���� : "+i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		};
		Runnable r4 =() -> {
			for(int i=10;i>=0;i--) {
				try {
					System.out.println("Thread D���� : "+i+"  ���׸��׽�Ʈ  "+number);
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