package pair;

public class ChildPair<T, V> extends Pair<T,V> {
	private T name;
	private V age;	
	public ChildPair(T name, V age) {
		super(name, age);	
	}
}
