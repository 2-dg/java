package upper_bounded_wildcard_compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class temp {

	public static void main(String[] args) {
		List<Integer> li = Arrays.asList(1,2,3);	//int[] a = new int[]{1,2,3};�迭�� LIST�÷��ǿ� ����
		List<Integer> li2 = new ArrayList<>();
		li2.add(new Integer(1));
		li2.add(new Integer(2));
		li2.add(new Integer(3));
		li2.add(new Integer(4));
		li2.add(new Integer(5));					//�÷��� �����ӿ�ũ�� ������ �ٵ�
		System.out.println(li2.get(2));
		for(Integer value : li2) {
			System.out.print(value+" ");
		}
		System.out.println();
		System.out.println(SumOfClass.sumOfList(li));
		System.out.println(SumOfClass.sumOfList(li2));
		
	}

}
