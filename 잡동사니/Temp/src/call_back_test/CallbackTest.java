package call_back_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallbackTest {
	public static void main(String[]args) {
		//ActionListener listener = new MyClass();
		//new ActionListener�ڸ��� listener�� �ְų� �ӽð�ü�� �ְų�
		//timer��ü�� 1000���� 1000�ʸ��� ���� �޼ҵ带 �����ϴ� ��ü�� �����
//		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("����");
//				
//			}
//		});
		//�̰� ���ٽ� ó��. �Ű��������� �츮�� ȭ��ǥ, �׸��� �߰�ȣ �ϳ� �����ָ� ��. �ǹ����� ���ٽ��� ��.
		//���ٽ� = ��ü�� �Լ��� ǥ���� ��. ��ȣ�� ���ֵ� �ǰ� �߰�ȣ�� ��� ��.
		javax.swing.Timer timer = new javax.swing.Timer(1000, (/*ActionEvent*/ e)-> {
				System.out.println("����");
		});
		timer.start();//Ÿ�̸� �����带 �ҷ��� �����Ѵ�
		//1�ʸ��� listener��ü�� �θ���. public void actionPerfomed(Ac~�� �����Ѵ�.
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				System.out.println("��ť");
				//1�ʵ��� ��������·� ����. �ٸ� ���α׷����� �� �ð� Ȱ�� ����.
			}
		}
	}
}