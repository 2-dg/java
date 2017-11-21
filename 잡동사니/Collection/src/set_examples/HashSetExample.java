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
		set.add("JAVA");   						//set�÷��ǿ��� ���� ��ü�� �ϳ��� ����ż� �̰� �� ��
		set.add("iBATIS");
		System.out.println("�� ��ü �� : "+set.size());
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
		set.remove("JDBC");
		set.remove("iBATIS");
		System.out.println("�� ��ü �� : "+set.size());
		for(String val : set) {
			System.out.println(val); 
		}
		set.clear();
		System.out.println(set.isEmpty());
		Set<Member> set1 = new HashSet<Member>();
		set1.add(new Member("ȫ�浿", 30));
		set1.add(new Member("ȫ�浿", 30)); //new�� �ؼ� ��ü�� �ٸ����� ���ü�̰� HashSet�� �ߺ���ü������ �� ��
		System.out.println(set1.size());  //�׷��� ����� 1�� ��� ��.
		String sdawd = "101011";
		int a = Integer.parseInt(sdawd);
		System.out.println(a);
		sdawd = String.valueOf(a);
		System.out.println(sdawd);
	}
}
