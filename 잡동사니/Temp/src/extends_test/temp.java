package extends_test; //슈퍼클래스
import java.util.Scanner;
public class temp extends Object{
	private int speed;
	public temp(int speed) {
		super();
		this.speed=speed;
		System.out.println("부모생성자");
	}
//	public temp() {
//		this(0); 		//5행의 생성자를 생성하고 매개변수에 0을 넣겠다
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