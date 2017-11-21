package thread_priorities;

public class PriorityTest {

	public static void main(String[] args) {
		for(int i=1;i<11;i++) {
			Thread thread = new MyThread((i)+"번 스레드입니다.");
			if(i==10) {
				thread.setPriority(Thread.MAX_PRIORITY);
				thread.setDaemon(true);
			}
			if(i==1) {
				thread.setPriority(Thread.MIN_PRIORITY);
				thread.setDaemon(true);
			}
			thread.start();
		}			
		System.out.println("다음 스레드가 종료됨 : "+Thread.currentThread().getName());
	}
}