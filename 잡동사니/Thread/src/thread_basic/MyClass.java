package thread_basic;

public class MyClass extends Thread {
	@Override
	public void run() {//���ÿ� ó���ϰ� ���� �͵��� ���⿡ �ִ´�
		for(int i=0;i<5;i++) {
			System.out.println("myclass  "+i);
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
