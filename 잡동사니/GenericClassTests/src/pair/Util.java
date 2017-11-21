package pair;
public class Util {
//	public static<T,V> V getValue(Pair<T,V> p, T name) {
//		if (p.getName().equals(name)) {
//			return p.getAge();
//		}else {
//			return null;	
//		}
//	}
//Pair<T,V>를 S로 표현하면 이런 식이 됨
	public static<S extends Pair<T,V>,T,V> V getValue(S p, T name) {
		if (p.getName().equals(name)) {
			return p.getAge();
		}else {
			return null;	
		}
	}
}