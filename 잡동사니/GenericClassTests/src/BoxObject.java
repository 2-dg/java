
public class BoxObject {
	private Object number;
	public BoxObject(Object number) {
		super();
		this.number = number;
	}
	public Object getNumber() {
		return number;
	}
	public void setNumber(Object number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BoxObject [number=" + number + "]";
	}
	

}
