package polymorphism;

public class BusTest implements Vehicle{
	private String Check = "";

	public BusTest(String check) {
		super();
		Check = check;
	}

	@Override
	public void run() {
		System.out.println("Bus is RUNNING!!!!!!!");
	}
	
	public void checkFare() {
		System.out.println(Check);
	}
}
