package sec01.test20170515;

public class BoxFloat {
	private float num;

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

	public BoxFloat(float num) {
		super();
		this.num = num;
	}

	@Override
	public String toString() {
		return "BoxFloat [num=" + num + "]";
	}
	

}
