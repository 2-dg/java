package anonymous;

import java.util.Arrays;
import java.util.List;

public class AnonymousAndLambda {
	public static void main(String[] args) {
		TV t = new TV();							//�̰� ���� Ŭ������ ���� ��ü �����̴�.
		RemoteControl ac = new RemoteControl() {	//����Ŭ������ �θ𿵿��� ��������
													//�����ϸ� �޼ҵ念���� �������� �ʴ´�.
			@Override
			public void turnOn() {
				System.out.println("turnOn()");
			}
			@Override
			public void turnOff() {
				System.out.println("turnOff()");
			}
		};
		ac.turnOn();
		ac.turnOff();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("������ ���� ��");
			}
		});
		Thread threadw = new Thread(()->
				System.out.println("���ٽ� ������ ���� ��"));
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		for(Integer val : list) {
			System.out.print(val+" ");
		}System.out.println();					//������
		List<Integer> list2 = Arrays.asList(1,2,3,4,5);
		list.forEach(n->System.out.print(n+" "));
		System.out.println();
		
		thread.start();
		threadw.start();
	}
}
