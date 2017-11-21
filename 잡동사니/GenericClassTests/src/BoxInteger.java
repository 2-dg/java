
public class BoxInteger {
	private Integer number;
	public BoxInteger(Integer number) {
		super();
		this.number = number;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BoxInteger [number=" + number + "]";
	}
	

}
