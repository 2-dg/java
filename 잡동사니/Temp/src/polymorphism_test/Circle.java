package polymorphism_test;

public class Circle extends Shape {
	private int Radius;
	//@Override
	public void draw() {
		//super.draw();
		System.out.println("Circle.draw()");
	}	
}