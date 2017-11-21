
public class PersonGeneric <T> implements Comparable<T> {
	private int height;
	private String name;
	public PersonGeneric(int height, String name) {
		super();
		this.height = height;
		this.name = name;
	}
	public PersonGeneric() {
		super();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PersonGeneric [height=" + height + ", name=" + name + "]";
	}
	@Override
	public int compareTo(T o) {
		if(this.height<((PersonGeneric)o).getHeight()) {
			return -1;
		}else return 0;
	}
}
