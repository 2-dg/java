package examples;

import java.util.Arrays;

public class Array2DPrint {
	public static void main(String[]args) {
		int[][] array = new int[][] {
			{1,2,3},
			{1,2,3,4},
			{1,2,3,4,5}
			};
			for(int i=0;i<array.length;i++) {
				for(int j=0; j<array[i].length;j++) {
					System.out.println(i+"행"+j+"열:"+array[i][j]);
				}
			System.out.println("===================");
			System.out.println(Arrays.toString(array));			//메모리주소출력
			System.out.println(Arrays.toString(array[0]));
			System.out.println(Arrays.toString(array[1]));		//이건 1차원 출력
			System.out.println(Arrays.toString(array[2]));
			for(int[] a:array) {
				for(int value:a) {
					System.out.print(value+ " ");
				}
				System.out.println("");
			}			
			}
	}
}
