package sec01.test20170515;

public class BoxDouble {
	private double num;

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public BoxDouble(double num) {
		super();
		this.num = num;
	}

	@Override
	public String toString() {
		return "BoxDouble [num=" + num + "]";
	}
	

}
