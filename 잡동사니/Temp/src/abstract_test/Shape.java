package abstract_test;

public abstract class Shape extends Object {
	private int x, y;
	public Shape() {
		this(0,0);	//제일 매개변수가 많은 애를 따라서 초기값 부여
	}	
	public Shape(int x, int y) {
		super();
		this.x=x;
		this.y=y;
		System.out.println("추상클래스 객체 생성");
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
		return "Shape [x=" + x + ", y=" + y + "]";
	}
	public void move(int x, int y) {
		this.x = x; this.y= y;
	}
	//자식은 반드시 아래의 추상메소드를 오버라이드해야 한다. 만약에 이게 n개라면 자식들도 전부 n개의 추상메소드를 포함해야 한다.
	public abstract void draw();
}