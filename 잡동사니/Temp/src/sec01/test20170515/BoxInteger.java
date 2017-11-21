package sec01.test20170515;

public class BoxInteger {
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BoxInteger(int num) {
		super();
		this.num = num;
	}

	@Override
	public String toString() {
		return "BoxInteger [num=" + num + "]";
	}
	
}
