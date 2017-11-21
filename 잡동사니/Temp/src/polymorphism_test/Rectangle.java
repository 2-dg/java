package polymorphism_test;

public class Rectangle extends Shape {
	private int width;
	private int height;
	//@Override
	public void draw() {
		//super.draw();
		System.out.println("Rectangle.draw()");
	}	
}