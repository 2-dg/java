package map_examples;

import java.util.Objects;

public class Box {
	private int number;
	private String name;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Box [number=" + number + ", name=" + name + "]";
	}
	public Box(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
	public Box() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		Box box = null;
		if(obj instanceof Box) {
			box=(Box)obj;
			return this.number==box.number&&this.name.equals(box.getName());
		}else {
			System.out.println("잘못된 객체 참조입니다.");
			return false;
		}
	}
	public boolean equals2(Object obj) {
		Box box = (Box)obj;
		return number==((Box)obj).getNumber()&&name.equals(((Box)obj).getName());
	}
	@Override
	public int hashCode() {
		return Objects.hash(name,number);
	}
}