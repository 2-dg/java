package interrupt;

public class InterruptMain {

	public static void main(String[] args) throws InterruptedException {
		ThreadA ta = new ThreadA();
		ta.start();
		Thread.sleep(3000);
		ta.interrupt();
		//ta.stop=true;
		System.out.println("Main is over");
	}

}
