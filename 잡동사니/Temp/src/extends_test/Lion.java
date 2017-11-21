package extends_test;

public class Lion extends Animal{
	private int legs;
	public Lion(int weight, String picture, int legs) {
		super(weight, picture);
		this.legs = legs;
	}
	public Lion() {
		this(0,null,0);
	}
	public int getLegs() {
		return legs;
	}
	public void setLegs(int legs) {
		this.legs = legs;
	}
	public void roar() {
		System.out.println("Lion의 roar함수 실행입니다.");
	}
	@Override
	public String toString() {
		return "Lion [legs=" + legs + ", toString()=" + super.toString() + "]";
	}
}