package wait_notify;

public class ThreadA extends Thread{	
	private WorkObject work;
	public ThreadA(WorkObject work) {
		super();
		this.work = work;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				work.methodA();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
