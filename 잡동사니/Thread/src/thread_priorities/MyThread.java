package thread_priorities;

public class MyThread extends Thread{
	public MyThread(String name) {
		setName(name);		//���� �����忡 �̸��� �ο�
		}

	@Override
	public void run() {
		for(long i=0;i<1_000_000;i++) {
			
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(long i=0;i<1_000_000;i++) {
			
		}
		System.out.println(getName()+"�����.!.!.!.!.!."+getPriority());
	}
}
