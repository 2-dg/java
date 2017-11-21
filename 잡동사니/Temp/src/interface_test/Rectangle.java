package interface_test;

public class Rectangle extends Object implements GraphicsObject {
	private int length;
	private int width;

	public Rectangle(int length, int width) {
		super();// ����Ŭ������ ������Ʈ ������. Object�� ���Ե� 11�� �Լ� ��ӹޱ�.
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
