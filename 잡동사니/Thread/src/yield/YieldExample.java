package yield;

public class YieldExample {
	public static void main(String[] args) throws InterruptedException {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();	
		threadA.start();		
		threadB.start();				//A�� B�� ���� ����
		
		Thread.sleep(3000);				//���ν����带 3�ʵ��� ������Ŵ. AB�� �����������̹Ƿ� �Լ� ����.
		threadB.work=false;				//A�� ����
		
		Thread.sleep(3000);				//3�ʵ��� ����
		threadB.work=true;				
		threadA.work=false;				//B�� ����
		
		Thread.sleep(3000);				//3�ʵ��� ����
		threadA.stop=true;
		threadB.stop=true;
		
		System.out.println("Main is over");

	}
}
