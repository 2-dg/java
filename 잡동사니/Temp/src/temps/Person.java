package temps;

import java.util.Arrays;

public class Person implements Comparable{
	private String name;
	private int height;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Person(String name, int height) {
		super();
		this.name = name;
		this.height = height;
	}
	public Person() {
		super();
	}
	@Override
	public int compareTo(Object o) {
		Person p=null;
		if(o instanceof Person) {
			p=(Person)o;
		}else {
			System.out.println("올바른 객체참조가 아닙니다.");
			return -2;
		}
		if(this.height-p.getHeight()==0) {
				return 0;
		}else if(this.height-p.getHeight()>0) {
			return 1;
		}else {
			return -1;
		}
	}
	@Override
	public void getMaximum(Person[] array) {
		getHeight();
		
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", height=" + height + ", getName()=" + getName() + ", getHeight()="
				+ getHeight() + "]";
	}
	
}
