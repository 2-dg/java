package demon_and_independent_threads;

public class MyThreadMain {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new MyThread());
		//t.setDaemon(true);           //���󽺷��尡 ��������� ����. �̰� ������ ����������� ����.
		t.setName("������ �̸� ����");
		t.start();
		Runnable r = () -> {
				System.out.println("Hi im Thread");	};
		Thread tt = new Thread(r);
		tt.start();
		for(int i=0;i<11;i++) {
			System.out.println("main"+i);
			Thread.sleep(500);
		}//end of for
		//������ ���� �������� �ұ��ϰ� t������� ��� ����Ǵµ� �̰��� ������������ �Ѵ�.
		System.out.println("���� ��");
	}
}