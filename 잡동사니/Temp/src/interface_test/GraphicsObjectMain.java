package interface_test;

public class GraphicsObjectMain {

	public static void main(String[] args) {
		Rectangle rec = new Rectangle(3, 4);
		GraphicsObject gra = new Rectangle(4,5);
		System.out.println(rec.getArea());
		rec.draw();
		gra.draw();
		System.out.println(gra.getArea());
		if(gra instanceof Rectangle) {
			System.out.println("gra�� Rectangle�� ���ؼ� ������ �������̽��Դϴ�.");
			Rectangle rec2 = (Rectangle)gra;	//gra�� ����Ŭ������ �� �� ���� ������ ����Ű���� Rectangle��
												//���� ����ȯ�� ������.
		}else {
			System.out.println("�ƴմϴ�.");
		}
		}
}
