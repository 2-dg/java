package pair;
public class Util {
//	public static<T,V> V getValue(Pair<T,V> p, T name) {
//		if (p.getName().equals(name)) {
//			return p.getAge();
//		}else {
//			return null;	
//		}
//	}
//Pair<T,V>�� S�� ǥ���ϸ� �̷� ���� ��
	public static<S extends Pair<T,V>,T,V> V getValue(S p, T name) {
		if (p.getName().equals(name)) {
			return p.getAge();
		}else {
			return null;	
		}
	}
}