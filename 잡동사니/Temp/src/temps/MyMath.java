package temps;

public class MyMath <T extends Number> {
	private T x;
	private T y;
	private T avg;
	public MyMath(T x, T y) {
		super();
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
	public double getAverage() {
		
		return ((double)(x.doubleValue()+y.doubleValue())/2);
	}
}