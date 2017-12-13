package polymorphism;

public class Driver {
	//Driver클래스에 Vehicle인터페이스를 패러미터로 하는 drive라는 메소드를 
	//생성하여서 해당 인터페이스를 구현하였으면 run();이라는 메소드는 꼭 구현해야 하기 때문에
	//Driver가 drive를 하면 Vehicle이 run하게 되게 만든 것이다.
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
}