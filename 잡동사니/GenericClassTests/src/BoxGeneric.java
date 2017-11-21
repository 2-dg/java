
public class BoxGeneric <T> {
	private T number;
	public BoxGeneric(T number) {
		super();
		this.number = number;
	}
	public T getNumber() {
		return number;
	}
	public void setNumber(T number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BoxGeneric [number=" + number + "]";
	}
}