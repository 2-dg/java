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
			System.out.println("gra는 Rectangle에 의해서 구현된 인터페이스입니다.");
			Rectangle rec2 = (Rectangle)gra;	//gra가 슈퍼클래스라서 좀 더 좁은 범위를 가르키지만 Rectangle로
												//강제 형변환시 가능함.
		}else {
			System.out.println("아닙니다.");
		}
		}
}
