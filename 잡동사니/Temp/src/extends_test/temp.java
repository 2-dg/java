package extends_test; //����Ŭ����
import java.util.Scanner;
public class temp extends Object{
	private int speed;
	public temp(int speed) {
		super();
		this.speed=speed;
		System.out.println("�θ������");
	}
//	public temp() {
//		this(0); 		//5���� �����ڸ� �����ϰ� �Ű������� 0�� �ְڴ�
//	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public String toString() {
		return "temp [speed=" + speed + "]";
	}	
}