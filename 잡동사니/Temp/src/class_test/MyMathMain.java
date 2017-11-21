package class_test;

public class MyMathMain {
	public static void main(String[]args) {
		MyMath myMath = new MyMath();
		System.out.println(myMath.toString());
		System.out.println(myMath);
		System.out.println(myMath.square(10));
		System.out.println(myMath.square(3.5));
	}
}