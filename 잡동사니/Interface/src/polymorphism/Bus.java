package polymorphism;

public class Bus implements Vehicle{

	@Override
	public void run() {
		System.out.println("Bus is RUNNING!!!!!!!");
	}
	
	public void checkFare() {
		System.out.println("Vehicle인터페이스를 구현했지만 Bus클래스에만 있는 checkFare메소드이다!!");
	}
	
}
