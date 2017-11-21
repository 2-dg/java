package p406;

public class Rectangle extends Object implements Comparable {
	private int width;
	private int height;

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}

	public int getArea() {
		return width * height;
	}

	@Override
	public int compareTo(Object o) {
		Rectangle r = null;
		if (o instanceof Rectangle) {
			r = (Rectangle) o;
		} else {
			System.out.println("o에는 자식영역 Rectangle이 존재하지 않음");
			return -10;
		}
		if(getArea()<r.getArea()) {
			return -1;
		}else if(getArea()>r.getArea()){
			return 1;
		}else {
			return 0;
		}
	}
}