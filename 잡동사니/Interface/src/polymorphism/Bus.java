package polymorphism;

public class Bus implements Vehicle{

	@Override
	public void run() {
		System.out.println("Bus is RUNNING!!!!!!!");
	}
	
	public void checkFare() {
		System.out.println("Vehicle�������̽��� ���������� BusŬ�������� �ִ� checkFare�޼ҵ��̴�!!");
	}
	
}
