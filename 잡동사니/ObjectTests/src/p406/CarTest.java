package p406;

public class CarTest {

	public static void main(String[] args) {
		Car firstCar = new Car("HWM520");
		Car secondCar = new Car("HWM520");
		if(firstCar.equals(secondCar)) {
			System.out.println("������ ������ ���Դϴ�.");
		}else {
			System.out.println("������ ������ ���� �ƴմϴ�.");
		}
	}
}
