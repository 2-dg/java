package abstract_test;

public class Rectangle extends Shape{
	private int width;
	private int height;
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width=width;
		this.height=height;
		System.out.println("�ڽİ�ü ����");
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
		return super.toString()+"Rectangle [width=" + width + ", height=" + height + "]";
	}


	@Override
	public void draw() {
		System.out.println("�������̵带 �� �ڽ��Լ�");
	}
}
