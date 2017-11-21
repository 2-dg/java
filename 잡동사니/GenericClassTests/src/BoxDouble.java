
public class BoxDouble {
	private Double number;

	public BoxDouble(Double number) {
		super();
		this.number = number;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "BoxDouble [number=" + number + "]";
	}
	

}
