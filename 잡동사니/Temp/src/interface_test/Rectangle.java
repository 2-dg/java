package interface_test;

public class Rectangle extends Object implements GraphicsObject {
	private int length;
	private int width;

	public Rectangle(int length, int width) {
		super();// 슈퍼클래스인 오브젝트 생성자. Object에 포함된 11개 함수 상속받기.
		this.length = length;
		this.width = width;
	}

	public Rectangle() {
		this(0, 0);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setDimensions(int length, int width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public int getArea() {
		System.out.println("Interface Overried getArea() Operated.");
		return length * width;
	}

	@Override
	public void draw() {
		System.out.println("Interface Overried draw() Operated.");

	}

	@Override
	public String toString() {
		return "Rectangle [length=" + length + ", width=" + width + ", getArea()=" + getArea() + "]";
	}

	@Override
	public void MyMethod() {
		// TODO Auto-generated method stub
		
	}

}
