package interrupt;

public class ThreadA extends Thread {
	public boolean stop = false;
	@Override
	public void run() {
		while(true) {
			System.out.println("�۾����Դϴ�.");
			try {
				Thread.sleep(100);					//������ �ִ� ������� ���ͷ�Ʈ�� �� �� �ִ�.
			} catch (InterruptedException e) {
				break;
			}
			if(Thread.interrupted()) {				//�����忡 ���ͷ�Ʈ�� �ɸ��� ������ ����
				break;
			}
			//if(isInterrupted())�� ���� ����� �Ѵ�. ���� ���� ����� isInterrupted.
			
		}
		System.out.println("ThreadA����");
	}
}