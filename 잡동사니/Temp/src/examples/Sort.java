package examples;

import java.util.Arrays;

public class Sort {
	public static void main(String[]args) {
		int[] num = new int[] {8,6,9,1};
		System.out.println(Arrays.toString(num));
		for(int value:num) {
			System.out.println(value);
		}
		Arrays.sort(num);
		System.out.println("==========");
		for(int value:num) {
			System.out.println(value);
		}
	}

}
