package interrupt;

public class ThreadA extends Thread {
	public boolean stop = false;
	@Override
	public void run() {
		while(true) {
			System.out.println("작업중입니다.");
			try {
				Thread.sleep(100);					//슬립이 있는 스레드는 인터럽트를 걸 수 있다.
			} catch (InterruptedException e) {
				break;
			}
			if(Thread.interrupted()) {				//스레드에 인터럽트가 걸리면 다음을 실행
				break;
			}
			//if(isInterrupted())도 같은 기능을 한다. 가장 좋은 방법은 isInterrupted.
			
		}
		System.out.println("ThreadA종료");
	}
}