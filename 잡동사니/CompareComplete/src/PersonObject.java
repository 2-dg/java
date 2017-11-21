
public class PersonObject implements Comparable {
	private int height;
	private String name;
	
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

	public PersonObject(int height, String name) {
		super();
		this.height = height;
		this.name = name;
	}
	public PersonObject() {
		super();
	}
	

	@Override
	public String toString() {
		return "PersonObject [height=" + height + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Object o) {
		PersonObject p = null;
		if(o instanceof PersonObject) {
			p=(PersonObject)o;
		}else {
			System.out.println("잘못된 객체 참조입니다.");
		}
		if(this.height<p.getHeight()) {
			return -1;
		}else {
			return 1;
		}
	}
}
