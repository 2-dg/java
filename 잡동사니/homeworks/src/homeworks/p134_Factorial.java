package homeworks;

import java.util.Scanner;

public class p134_Factorial {

	public static void main(String[] args) {
		long fact=1;
		int n=0;
		System.out.print("정수를 입력하시오 : ");
		Scanner scan = new Scanner(System.in);
		n=Integer.parseInt(scan.nextLine());
		for(int i=1; i<=n; i++) {
			fact*=i;
		}
		System.out.printf("%d!은 %d입니다.",n, fact);

	}

}
