package interface_test;

public class FlyingCar2 extends Car implements Flyable{	
	public FlyingCar2(int speed) {
		super(speed);
	}

	@Override
	public void fly() {
		System.out.println(getSpeed()+"FFFFFFFFFFFLLLLLLLLLLLYYYYYYYYYYYYYY");
	}

	public static void main(String[] args) {
		FlyingCar2 f = new FlyingCar2(100);
		f.fly();
		f.setSpeed(200);
		f.fly();
		((Flyable)f).fly();
		

	}

}
