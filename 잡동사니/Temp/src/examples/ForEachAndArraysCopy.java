package examples;

import java.util.Arrays;

public class ForEachAndArraysCopy {
	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 4 };
		int[] copy;
		int[] num = Arrays.copyOf(numbers,numbers.length);
		
		copy=numbers;										//주소만 참조하므로 numbers를 바꾸면 copy도 바뀜
		
		
		copy[0]=10;
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		System.out.println("구분선");
		for (int value : numbers) {							//이게 for each문
			System.out.println(value + " ");
		}
		System.out.println("=============");
		for (int i = 0; i < copy.length; i++) {
			System.out.println(numbers[i]);
		}
		System.out.println("구분선");
		for (int value : copy) {
			System.out.println(value + " ");
		}
		System.out.println("=============");
		for (int i = 0; i < copy.length; i++) {
			copy[i]=numbers[i];
			System.out.println(numbers[i]);
			System.out.println(copy[i]);
		}
		System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.toString(copy));
		System.out.println(Arrays.toString(num));

	}

}
