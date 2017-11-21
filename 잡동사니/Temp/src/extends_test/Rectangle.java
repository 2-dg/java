package extends_test;

public class Rectangle extends Shape{
	private int width;
	private int height;
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	};
	public Rectangle() {
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + ", toString()=" + super.toString() + "]";
	}
	public double area() {
		System.out.println("area�Լ� ���� = "+width*height);
		return width*height;
	}
	public void draw() {
		System.out.println("draw�Լ� ����. x��ǥ = "+getX()+",y��ǥ = "+getY()+"�簢���� �о� = "+area());
	}
}
