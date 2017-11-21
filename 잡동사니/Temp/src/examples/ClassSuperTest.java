package examples;

import singletone_test.temp2;

public class ClassSuperTest extends Object {
	private int a;

	public ClassSuperTest() {
		this(10);
	}

	public ClassSuperTest(int a) {

	}

	void sub() {
		int[] a1 = { 3, 4, 5 };
	}

	public static void main(String[] args) {
		ClassSuperTest p = new ClassSuperTest(3);
		p.sub(); // 식으로 접근은 가능하지만 생성자로는

	}

}
