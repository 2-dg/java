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
		System.out.println("area함수 실행 = "+width*height);
		return width*height;
	}
	public void draw() {
		System.out.println("draw함수 실행. x좌표 = "+getX()+",y좌표 = "+getY()+"사각형의 넓어 = "+area());
	}
}
