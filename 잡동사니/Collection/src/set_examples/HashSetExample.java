package set_examples;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("JAVA");
		set.add("JDBC");
		set.add("Servlet/JSP");
		set.add("JAVA");   						//set컬렉션에선 같은 객체는 하나만 저장돼서 이건 안 됨
		set.add("iBATIS");
		System.out.println("총 객체 수 : "+set.size());
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
		set.remove("JDBC");
		set.remove("iBATIS");
		System.out.println("총 객체 수 : "+set.size());
		for(String val : set) {
			System.out.println(val); 
		}
		set.clear();
		System.out.println(set.isEmpty());
		Set<Member> set1 = new HashSet<Member>();
		set1.add(new Member("홍길동", 30));
		set1.add(new Member("홍길동", 30)); //new를 해서 객체는 다르지만 동등객체이고 HashSet은 중복객체저장이 안 됨
		System.out.println(set1.size());  //그래서 사이즈가 1로 출력 됨.
		String sdawd = "101011";
		int a = Integer.parseInt(sdawd);
		System.out.println(a);
		sdawd = String.valueOf(a);
		System.out.println(sdawd);
	}
}
