package wait_notify;

public class WorkObject {
	public synchronized void methodA() throws InterruptedException {
		System.out.println("ThreadA�� methodA()�� �����߽��ϴ�.");
		notify();
		wait();			
		//��ũ�γ������(����ȭó��)�ϸ� ��ȣ���� �Ӱ迵���� �����Ǵ� ����
		//�Ӱ迵�� : �Լ��� �Ϸ�Ǵ� ���� ���� �Ұ�
	}
	public synchronized void methodB() throws InterruptedException {
		System.out.println("ThreadB�� methodB()�� �����߽��ϴ�.");
		notify();
		wait();
	}
}