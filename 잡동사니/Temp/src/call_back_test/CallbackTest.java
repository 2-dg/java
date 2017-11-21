package call_back_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallbackTest {
	public static void main(String[]args) {
		//ActionListener listener = new MyClass();
		//new ActionListener자리에 listener를 넣거나 임시객체를 넣거나
		//timer객체는 1000분의 1000초마다 뒤의 메소드를 실행하는 객체로 만든다
//		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("ㅎㅎ");
//				
//			}
//		});
		//이건 람다식 처리. 매개변수까지 살리고 화살표, 그리고 중괄호 하나 지워주면 끝. 실무에선 람다식을 씀.
		//람다식 = 객체를 함수로 표현한 것. 괄호도 없애도 되고 중괄호도 없어도 됨.
		javax.swing.Timer timer = new javax.swing.Timer(1000, (/*ActionEvent*/ e)-> {
				System.out.println("ㅎㅎ");
		});
		timer.start();//타이머 스레드를 불러서 실행한다
		//1초마다 listener객체를 부른다. public void actionPerfomed(Ac~을 실행한다.
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				System.out.println("땡큐");
				//1초동안 실행대기상태로 유지. 다른 프로그램들이 이 시간 활용 가능.
			}
		}
	}
}