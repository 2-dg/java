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
		MyClassMain t = new MyClassMain();			//스스로를 객체로 만듬(디폴트 생성자로. 생성자가 없으면 객체로 안불려옴.)
		MyClass newOBJ = t.doSomething();			//newOBJ를 메인의 dosomething이라는 함수의 객체로 만듦.
		newOBJ = new MyClass();						//가비지 컬렉터에 의해서 newOBJ는 중복된 객체를 가리키게 되므로
													//13열에서 생성된 객체는 붕 떠버리므로 사라짐.(14열에서 새로 생성된 객체가
													//13열에서 생성된 객체를 잡아먹으므로.)
	}
}