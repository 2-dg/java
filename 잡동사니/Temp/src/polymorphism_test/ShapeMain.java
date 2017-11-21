package polymorphism_test;

public class ShapeMain {

	public static void main(String[] args) {
//		Shape s1, s2, s3, s4;
//		s1=new Shape();
//		s2=new Rectangle();
//		s3=new Triangle();
//		s4=new Circle();
//		
//		s1.draw();
//		s2.draw();
//		s3.draw();
//		s4.draw();
		Shape[] s = new Shape[4]; //혹은
		Shape[] d = new Shape[] {new Shape(),new Rectangle(), new Triangle(), new Circle()};
		for(Shape s1:d) {
			s1.draw();
		}
		System.out.println("=========구분선=========");
		for(int i = 0; i<d.length;i++) {
			d[i].draw();
		}
	}
}
