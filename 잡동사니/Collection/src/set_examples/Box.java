package set_examples;

import java.util.Objects;

public class Box implements Comparable {
	private int number;
	private String name;
	public Box(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
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
	@Override
	public boolean equals(Object obj) {				//이걸 나중에 하게 됨.
		Box box=null;
		if(obj instanceof Box) {
			box = (Box)obj;
		}else {
			return false;
		}
		return this.number == box.getNumber() && name.equals(box.getName());
	}			//이 조건을 통해서 기본키를 정하는 것이다. 뒤의 이퀄스를 없애면 키값이 number가 되어 number가 같으면 들어간다.
				//대신 뒤의 이퀄스를 없애면 밑의 해쉬코드값도 비교해야 하므로 해쉬코드의 name도 없애야 한다.
				//그러니까 그냥 String이나 int 등의 기본 자료형으로 저장할 시에는 동일 클래스의 동일 값으로
				//저장되므로 같은 해쉬끼리 비교해서 막아주지만 사용자가 만든 객체를 Set에 저장시킬 경우엔
				//각자 객체들이 가진 해쉬값이 다르기 때문에 다 들어간다. 그걸 막기 위해서 사용자가 이퀄스와 해쉬코드를 구현하는 것이다.
	@Override
	public int hashCode() {
		return Objects.hash(name,number);		//해쉬코드를 생성해서 넣어주는 것. 이걸 먼저 하고
		
	}
	
	@Override
	public int compareTo(Object o) {
		Box box=null;
		if(o instanceof Box) {
			box = (Box)o;
		}else {
			System.out.println("잘못된 객체참조입니다.");
			return 0;
		}
		if(this.number == box.getNumber()) {
			return 1;
		}else return -1;
	}
}