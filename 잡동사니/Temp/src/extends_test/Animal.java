package extends_test;

public class Animal extends Object {
	private int weight;
	private String picture;
	public Animal(int weight, String picture) {
		super();
		this.weight=weight;
		this.picture=picture;
	}
	public Animal() {
		this(0,null);
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void eat() {
		System.out.println("eat함수 실행입니다.");
	}
	public void sleep() {
		System.out.println("sleep함수 실행입니다.");
	}
	@Override
	public String toString() {
		return "Animal [weight=" + weight + ", picture=" + picture + "]";
	}
}
