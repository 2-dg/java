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
				throw new MyException("으아아아아아ㅏ오류발생");
			} else {
				throw new MyException();
			}
		} catch (MyException e) {
			e.printStackTrace();// MyException이 Exception을 상속받았기 때문에 이런 메소드들을 쓸 수 있는 것이다.
			e.getMessage(); // getMessage메소드가 MyException의 생성자를 부르면서 오류메세지 출력
		}
	}
}