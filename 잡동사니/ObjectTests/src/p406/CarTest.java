package p406;

public class CarTest {

	public static void main(String[] args) {
		Car firstCar = new Car("HWM520");
		Car secondCar = new Car("HWM520");
		if(firstCar.equals(secondCar)) {
			System.out.println("동일한 종류의 차입니다.");
		}else {
			System.out.println("동일한 종류의 차가 아닙니다.");
		}
	}
}
