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
		list.add(2, "DB"); // �̷����ϸ� 2�� �ڸ��� DB�� ���� Ser~�� 3���� �з���.
		list.add("IBatis"); // �̰� 4�� ����
		System.out.println(list.size()); // �� 10���� �����ȴٸ鼭?
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
		List<String> list1 = Arrays.asList("ȫ�浿", "�ſ��", "���ڹ�");
		for (String name : list1) {
			System.out.println(name);
		}
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		for (int value : list2) {
			System.out.println(value);
		}
		

	}

}
