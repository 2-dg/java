package call_back_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyClass extends Object implements ActionListener{

	
	//�̺�Ʈ�� �߻��ϸ� �� �Լ� ȣ��. �׷��鼭 ��ü�������� e�� �̺�Ʈ ������ �Ѿ�´�.
	//������ �����ϳĸ� �̺�Ʈ�� �߻��� �� �ؾߵ� ���� �����ض�.
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ȭƼ��");
		
	}
	//����Ʈ ������	
	public MyClass() {
		super();
	}
	

}
