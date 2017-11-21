package thread_basic;

public class MyClass3 implements Runnable{

	@Override
	public void run() {//동시에 처리하고 싶은 것들을 여기에 넣는다
		for(int i=0;i<5;i++) {
			System.out.println("myclass3 "+i);
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}	
}
}