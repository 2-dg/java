package thread_basic;

import java.awt.Toolkit;

public class MyThread {
	public static Toolkit tool = Toolkit.getDefaultToolkit();
	public static void main(String[] args) throws InterruptedException {
		MyClass my = new MyClass();						//5개중 1번 방법
		MyClass2 my2 = new MyClass2();
		Runnable my3 = new MyClass3();					//2번 방법
		Thread tmy2 = new Thread(my3);
		Thread tmy = new Thread(new MyClass3());		//식으로도 됨
		tmy2.start();
		my.start();             				//안에 있는 run의 스레드화. 밑의 for문과 동시에 실행 됨
		my2.start();
		Thread tmy3 = new Thread(new Runnable() {   //3번째 방법. 임시객체
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("임시객체 "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
			}
			}
		});
		tmy3.start();
		Thread tmy4 = new Thread(() -> {		 //람다식(4번째)
				for(int i=0;i<5;i++) {
					System.out.println("람다식 "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}			
		});
		tmy4.start();
		Thread my5 = new Thread() {				//5번째방법은 안 씀
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("5번째 "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			}
		};
		my5.start();
		
		
		
		
		
		for(int i=0;i<5;i++) {
			tool.beep();
			Thread.sleep(750);					//가장 좋은 건 Runnable인터페이스를 상속받은 Myclass3형태이다.
			System.out.println("beep");			//이거하고 임시객체 형태 (2번3번)
			//main에서 써야할 줄은 늘어나지만 스레드를 상속받는 형태는 다른 클래스
			//상속의 기회를 잃어버린다. 하지만 Runnable인터페이스를 상속받으면
			//다른 클래스의 상속이 가능하기 때문에 좋다!
		}
		
		
		
		
		
		
		
	}
}