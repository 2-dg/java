package thread_basic;

import java.awt.Toolkit;

public class MyThread {
	public static Toolkit tool = Toolkit.getDefaultToolkit();
	public static void main(String[] args) throws InterruptedException {
		MyClass my = new MyClass();						//5���� 1�� ���
		MyClass2 my2 = new MyClass2();
		Runnable my3 = new MyClass3();					//2�� ���
		Thread tmy2 = new Thread(my3);
		Thread tmy = new Thread(new MyClass3());		//�����ε� ��
		tmy2.start();
		my.start();             				//�ȿ� �ִ� run�� ������ȭ. ���� for���� ���ÿ� ���� ��
		my2.start();
		Thread tmy3 = new Thread(new Runnable() {   //3��° ���. �ӽð�ü
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("�ӽð�ü "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
			}
			}
		});
		tmy3.start();
		Thread tmy4 = new Thread(() -> {		 //���ٽ�(4��°)
				for(int i=0;i<5;i++) {
					System.out.println("���ٽ� "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}			
		});
		tmy4.start();
		Thread my5 = new Thread() {				//5��°����� �� ��
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("5��° "+i);
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			}
		};
		my5.start();
		
		
		
		
		
		for(int i=0;i<5;i++) {
			tool.beep();
			Thread.sleep(750);					//���� ���� �� Runnable�������̽��� ��ӹ��� Myclass3�����̴�.
			System.out.println("beep");			//�̰��ϰ� �ӽð�ü ���� (2��3��)
			//main���� ����� ���� �þ���� �����带 ��ӹ޴� ���´� �ٸ� Ŭ����
			//����� ��ȸ�� �Ҿ������. ������ Runnable�������̽��� ��ӹ�����
			//�ٸ� Ŭ������ ����� �����ϱ� ������ ����!
		}
		
		
		
		
		
		
		
	}
}