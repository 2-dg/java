package homeworks;

import java.util.Random;
import java.util.Scanner;

public class p138_RandomNumber2 {
	public static void main(String[]args) {
		Random generator = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.print("난수의 개수 : ");
		int count = Integer.parseInt(scan.nextLine());
		int sum = 0;
		
		for(int i=0; i<count ; i++) {
			int number=generator.nextInt(100);
			sum+=number;
		}
		System.out.print("난수"+count+"개의 합은" +sum);
	}

}
