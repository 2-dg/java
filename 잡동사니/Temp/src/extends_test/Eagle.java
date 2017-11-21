package extends_test;

public class Eagle extends Animal {
	private int wings;
	public Eagle(int weight, String picture, int wings) {
		super(weight, picture);
		this.wings = wings;
	}
	public Eagle() {
		this(0,null,0);
	}
	public int getWing() {
		return wings;
	}
	public void setWing(int wings) {
		this.wings = wings;
	}

	
	@Override
	public String toString() {
		return "Eagle [wings=" + wings + ", toString()=" + super.toString() + "]";
	}
	public void fly() {
		System.out.println("Eagle의 Fly함수 실행입니다.");
	}

}
