package sec01.test20170515;

public class BoxObject {
	private Object a;

	public BoxObject(Object a) {
		super();
		this.a = a;
	}

	public Object getA() {
		return a;
	}

	public void setA(Object a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "BoxObject [a=" + a + "]";
	}
	
}
