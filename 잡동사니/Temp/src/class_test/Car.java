package class_test;

public class Car {
	private String color;
	private int gear;
	private int speed;
	public Car(String color, int gear, int speed) {
		super();
		this.color = color;
		this.gear = gear;
		this.speed = speed;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color=color;
	}
	public int getGear() {
		return gear;
	}
	public void setGear(int gear) {
		this.gear = gear;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
