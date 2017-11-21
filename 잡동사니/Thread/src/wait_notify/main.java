package wait_notify;

public class main {

	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		ThreadA ta = new ThreadA(work);
		ThreadB tb = new ThreadB(work);
		ta.start();
		tb.start();
		
	}
}