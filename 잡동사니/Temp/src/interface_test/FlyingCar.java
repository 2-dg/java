package interface_test;

public class FlyingCar extends Object implements Drivable, Flyable{
	public static int count=0;
	public FlyingCar() {
		super();
		count++;
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public void fly() {
		System.out.println("fly");
		
	}

	@Override
	public void drive() {
		System.out.println("drive");
		
	}



}
