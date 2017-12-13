
public class CarExample {
	public static void main(String[] args) {
		//Tire인터페이스를 구현한 모듈인 HankookTire가 4개 장착된 Car라는 객체를 생성
		Car myCar = new Car();
		//그 안에 run();이라는 메소드가 구현돼있는데
		//이는 장착돼있는 타이어종류를 확인하는 메소드로서
		//Tire인터페이스에 구현돼있는 roll();을 Override한 모듈들을
		//전부 작동시키는 메소드라 보면 편함
		myCar.run();
		
		myCar.frontLeftTire = new KumhoTire();
		myCar.frontRightTire = new KumhoTire();
		//같은 인터페이스를 상속받아 만든 클래스이기 때문에
		//Tire클래스로 생성한 Tire에 각각 대입이 가능한 거임
		myCar.run();
		//다시 실행했을 때 타이어가 바뀌어껴진걸 볼 수 있음.
		//Tire라는 인터페이스로 금호타이어와 한국타이어를 규격화했기 때문에
		//교체가 가능하다고 생각하면 편함.
		
	}
}
