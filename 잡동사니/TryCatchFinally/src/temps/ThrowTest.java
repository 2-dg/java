package temps;

public class ThrowTest {
	public static void main(String[] args) {
		method1();
	}

	public static void method1() {
		int num = 0;
		try {
			if (num == 9) {
				// throw new MyException();
				throw new MyException("���ƾƾƾƾƤ������߻�");
			} else {
				throw new MyException();
			}
		} catch (MyException e) {
			e.printStackTrace();// MyException�� Exception�� ��ӹ޾ұ� ������ �̷� �޼ҵ���� �� �� �ִ� ���̴�.
			e.getMessage(); // getMessage�޼ҵ尡 MyException�� �����ڸ� �θ��鼭 �����޼��� ���
		}
	}
}