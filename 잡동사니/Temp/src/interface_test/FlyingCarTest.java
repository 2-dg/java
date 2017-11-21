package interface_test;

public class FlyingCarTest {

	public static void main(String[] args) {
		FlyingCar f1 = new FlyingCar();
		FlyingCar f2 = new FlyingCar();

		f1.drive();
		f1.fly();

		Drivable d = new FlyingCar();
		d.drive();
		// d.fly();
		Flyable f = new FlyingCar();
		f.fly();
		// f.drive();
		if (d instanceof FlyingCar) {
			((FlyingCar) d).drive();
			((FlyingCar) d).fly();
			System.out.println("=============");
			((Flyable) d).fly();
		}

	}

}
