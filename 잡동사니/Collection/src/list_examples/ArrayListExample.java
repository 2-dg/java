package list_examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("JAVA");
		list.add("Hello C World");
		list.add("Servlet/JSP");
		list.add(2, "DB"); // 이렇게하면 2번 자리에 DB가 가고 Ser~은 3으로 밀려남.
		list.add("IBatis"); // 이건 4에 저장
		System.out.println(list.size()); // 왜 10개씩 증가된다면서?
		System.out.println(list.get(2));
		System.out.println(list.get(3) + "\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + " : " + list.get(i));
		}
		System.out.println();
		list.remove(2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + " : " + list.get(i));
		}
		list.remove(2);
		list.remove("IBatis");
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + " : " + list.get(i));
		}
		List<String> list1 = Arrays.asList("홍길동", "신용권", "감자바");
		for (String name : list1) {
			System.out.println(name);
		}
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		for (int value : list2) {
			System.out.println(value);
		}
		

	}

}
