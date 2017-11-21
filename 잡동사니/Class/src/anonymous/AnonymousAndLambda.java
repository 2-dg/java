package anonymous;

import java.util.Arrays;
import java.util.List;

public class AnonymousAndLambda {
	public static void main(String[] args) {
		TV t = new TV();							//이게 보통 클래스를 통한 객체 생성이다.
		RemoteControl ac = new RemoteControl() {	//무명클래스는 부모영역을 기준으로
													//생성하며 메소드영역을 차지하지 않는다.
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
				System.out.println("스레드 실행 중");
			}
		});
		Thread threadw = new Thread(()->
				System.out.println("람다식 스레드 실행 중"));
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		for(Integer val : list) {
			System.out.print(val+" ");
		}System.out.println();					//보통방법
		List<Integer> list2 = Arrays.asList(1,2,3,4,5);
		list.forEach(n->System.out.print(n+" "));
		System.out.println();
		
		thread.start();
		threadw.start();
	}
}
