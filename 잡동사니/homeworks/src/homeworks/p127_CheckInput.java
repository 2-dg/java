package homeworks;

import java.util.Scanner;

public class p127_CheckInput {
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		int month=0;
		do {
			System.out.print("올바른 월을 입력하시오 [1~12] : ");
			month = Integer.parseInt(input.nextLine());
		}while(month<1||month>12);
		System.out.print("사용자가 입력한 월은 "+month);
	}

}
