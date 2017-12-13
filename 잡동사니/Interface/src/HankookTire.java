public class HankookTire implements Tire{
	@Override
	public void roll() {
		System.out.println("HankookTire is Rolling!");
	}	
}
//타이어인터페이스를 구현하는 클래스이므로 Tire에서 선언한 메소드는 구현해야 함