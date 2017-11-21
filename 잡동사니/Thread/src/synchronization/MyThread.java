package synchronization;

public class MyThread extends Thread{
	public Counter counter;

	public MyThread(Counter counter) {
		super();
		this.counter = counter;
	}

	@Override
	public void run() {
		for(int i=0;i<2000;i++) {
			counter.increment();
			counter.decrement();
			counter.printCounter();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
}