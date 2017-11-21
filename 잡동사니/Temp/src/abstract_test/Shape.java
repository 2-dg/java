package abstract_test;

public abstract class Shape extends Object {
	private int x, y;
	public Shape() {
		this(0,0);	//���� �Ű������� ���� �ָ� ���� �ʱⰪ �ο�
	}	
	public Shape(int x, int y) {
		super();
		this.x=x;
		this.y=y;
		System.out.println("�߻�Ŭ���� ��ü ����");
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
	//�ڽ��� �ݵ�� �Ʒ��� �߻�޼ҵ带 �������̵��ؾ� �Ѵ�. ���࿡ �̰� n����� �ڽĵ鵵 ���� n���� �߻�޼ҵ带 �����ؾ� �Ѵ�.
	public abstract void draw();
}