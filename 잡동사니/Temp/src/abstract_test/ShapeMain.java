package abstract_test;

public class ShapeMain {

	public static void main(String[] args) {
		//Shape shape = new Shape(10,20); �߻�Ŭ������ �ܵ����� ��ü���� �Ұ���
		Rectangle r = new Rectangle(10, 10, 200, 300);
		r.draw();
		System.out.println(r);
	}
}