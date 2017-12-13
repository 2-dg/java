/*자, Tire인터페이스가 만들어지고
 * Tire를 구현하는 Kumho와 Hankook이라는 모듈이 만들어지고
 * 그 모듈을 담은 더 큰 모듈인 Car를 생성
 */
public class Car {
	//Tire인터페이스모양의 메모리영역을 생성하고
	//그 안에 그 인터페이스를 구현한 한국타이어 모듈을 생성하여 장착
	Tire frontLeftTire = new HankookTire();
	Tire frontRightTire = new HankookTire();
	Tire rearLeftTire = new HankookTire();
	Tire rearRightTire = new HankookTire();
	
	void run() {
		frontLeftTire.roll();
		frontRightTire.roll();
		rearLeftTire.roll();
		rearRightTire.roll();
	}
}
