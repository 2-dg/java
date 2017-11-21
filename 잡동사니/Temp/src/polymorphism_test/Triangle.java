package polymorphism_test;

public class Triangle extends Shape {
	private int base;
	private int height;
	//@Override
	public void draw() {
		//super.draw();
		System.out.println("Triangle.draw()");
	}	
}