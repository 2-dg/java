package thread_basic;

public class MyClass2 extends Thread {
	@Override
	public void run() {//���ÿ� ó���ϰ� ���� �͵��� ���⿡ �ִ´�
		for(int i=5;i>0;i--) {
			System.out.println("myclass2 "+i);
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
