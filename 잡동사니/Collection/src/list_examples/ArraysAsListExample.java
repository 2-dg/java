package list_examples;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("ȫ�浿", "�ſ��", "���ڹ�");
		//List<String> list1 = Arrays.asList(new String("ȫ�浿"),new String ("�ſ��"), "���ڹ�");�� ����
		for(String val : list1) {
			System.out.println(val);
		}
		System.out.println();
		List<Integer> list2 = Arrays.asList(1,2,3);
		//List<Integer> list2 = Arrays.asList(new Integer(1),2,3);�� ����
		for(int val : list2) {
			System.out.println(val);
		}
		for(Integer val : list2) {
			System.out.println(val);
		}
		
	}

}
