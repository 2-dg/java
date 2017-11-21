package list_examples;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("홍길동", "신용권", "감자바");
		//List<String> list1 = Arrays.asList(new String("홍길동"),new String ("신용권"), "감자바");가 정석
		for(String val : list1) {
			System.out.println(val);
		}
		System.out.println();
		List<Integer> list2 = Arrays.asList(1,2,3);
		//List<Integer> list2 = Arrays.asList(new Integer(1),2,3);가 정석
		for(int val : list2) {
			System.out.println(val);
		}
		for(Integer val : list2) {
			System.out.println(val);
		}
		
	}

}
