package extends_test;

public class Shape {
	private int x;
	private int y;
	public Shape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Shape() {
		this(0,0);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Shape [x=" + x + ", y=" + y + ", toString()=" + super.toString() + "]";
	}
	public void print() {
		System.out.println("print함수 실행입니다. x="+x+"y="+y);
	}
}
