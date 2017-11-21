package five_ways_of_threads;

public class ThreadB implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("¶ò2");
		}
		
	}

}
