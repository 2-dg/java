package examples;

import java.util.Arrays;

public class ForEachAndArraysCopy {
	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 4 };
		int[] copy;
		int[] num = Arrays.copyOf(numbers,numbers.length);
		
		copy=numbers;										//�ּҸ� �����ϹǷ� numbers�� �ٲٸ� copy�� �ٲ�
		
		
		copy[0]=10;
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		System.out.println("���м�");
		for (int value : numbers) {							//�̰� for each��
			System.out.println(value + " ");
		}
		System.out.println("=============");
		for (int i = 0; i < copy.length; i++) {
			System.out.println(numbers[i]);
		}
		System.out.println("���м�");
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
