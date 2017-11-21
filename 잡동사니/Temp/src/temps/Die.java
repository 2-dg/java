package temps;

public class Die {
	private int face;

	public Die() {
		super();
		this.face = 1;
	}
	public int roll() {
		return (int)(Math.random()*(3+1-1)+1);
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	@Override
	public String toString() {
		return "Die [face=" + face + ", roll()=" + roll() + ", getFace()=" + getFace() + "]";
	}
	

}
