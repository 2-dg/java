package homeworks;

import java.util.Scanner;

public class p127_CheckInput {
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		int month=0;
		do {
			System.out.print("�ùٸ� ���� �Է��Ͻÿ� [1~12] : ");
			month = Integer.parseInt(input.nextLine());
		}while(month<1||month>12);
		System.out.print("����ڰ� �Է��� ���� "+month);
	}

}
