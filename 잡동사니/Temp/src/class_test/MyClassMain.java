package class_test;

public class MyClassMain {
	public MyClassMain() {
		super();
	}
	public MyClass doSomething() {
		MyClass b = new MyClass();
		return b;
	}
	public static void main(String[] args) {
		MyClassMain t = new MyClassMain();			//�����θ� ��ü�� ����(����Ʈ �����ڷ�. �����ڰ� ������ ��ü�� �Ⱥҷ���.)
		MyClass newOBJ = t.doSomething();			//newOBJ�� ������ dosomething�̶�� �Լ��� ��ü�� ����.
		newOBJ = new MyClass();						//������ �÷��Ϳ� ���ؼ� newOBJ�� �ߺ��� ��ü�� ����Ű�� �ǹǷ�
													//13������ ������ ��ü�� �� �������Ƿ� �����.(14������ ���� ������ ��ü��
													//13������ ������ ��ü�� ��Ƹ����Ƿ�.)
	}
}