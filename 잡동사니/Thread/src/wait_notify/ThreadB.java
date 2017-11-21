package wait_notify;

public class ThreadB extends Thread{	
	private WorkObject work;
	public ThreadB(WorkObject work) {
		super();
		this.work = work;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				work.methodB();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
