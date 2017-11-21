package pair;

public class Pair <T,V> {
	private T name;
	private V age;
	public Pair(T name, V age) {
		super();
		this.name = name;
		this.age = age;
	}
	public T getName() {
		return name;
	}
	public void setName(T name) {
		this.name = name;
	}
	public V getAge() {
		return age;
	}
	public void setAge(V age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Pair [name=" + name + ", age=" + age + "]";
	}
}