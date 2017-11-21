package abstract_test;

public class ShapeMain {

	public static void main(String[] args) {
		//Shape shape = new Shape(10,20); 추상클래스는 단독으로 객체생성 불가능
		Rectangle r = new Rectangle(10, 10, 200, 300);
		r.draw();
		System.out.println(r);
	}
}