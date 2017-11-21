package executors;

public class ThreadA implements Runnable {
	@Override
	public void run() {
		for(int i=10;i>=0;i--) {
			try {
				System.out.println("Thread A½ÇÇà : "+i);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}