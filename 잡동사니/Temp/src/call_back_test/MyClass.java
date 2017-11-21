package call_back_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyClass extends Object implements ActionListener{

	
	//이벤트가 발생하면 이 함수 호출. 그러면서 객체참조변수 e에 이벤트 정보가 넘어온다.
	//무엇을 구현하냐면 이벤트가 발생할 때 해야될 일을 구현해라.
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("화티이");
		
	}
	//디폴트 생성자	
	public MyClass() {
		super();
	}
	

}
