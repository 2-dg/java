package random_generic;

import java.util.ArrayList;

public class RandomList <T>{
	ArrayList<T> al = new ArrayList<T>();
	public void add(T item) {
		al.add(item);
	}
	public T select() {
		int num = (int)(Math.random()*(al.size()));
		return al.get(num);
	}
}