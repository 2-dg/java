package demon_and_independent_threads;

public class MyThreadMain {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new MyThread());
		//t.setDaemon(true);           //데몬스레드가 만들어지는 순간. 이게 없으면 독립스레드로 존재.
		t.setName("스레드 이름 설정");
		t.start();
		Runnable r = () -> {
				System.out.println("Hi im Thread");	};
		Thread tt = new Thread(r);
		tt.start();
		for(int i=0;i<11;i++) {
			System.out.println("main"+i);
			Thread.sleep(500);
		}//end of for
		//메인이 먼저 끝남에도 불구하고 t스레드는 계속 실행되는데 이것을 독립스레드라고 한다.
		System.out.println("메인 끗");
	}
}