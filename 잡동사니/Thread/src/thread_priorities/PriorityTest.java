package thread_priorities;

public class PriorityTest {

	public static void main(String[] args) {
		for(int i=1;i<11;i++) {
			Thread thread = new MyThread((i)+"�� �������Դϴ�.");
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
		System.out.println("���� �����尡 ����� : "+Thread.currentThread().getName());
	}
}