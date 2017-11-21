package examples;

public class OuterClassMain {
	public static void main(String[]args) {
		OuterClass out = new OuterClass(200);
		OuterClass.InnerClass in = out.new InnerClass(300);
		System.out.println("InnerClass's private int ia = "+in.getIa());
	}
}