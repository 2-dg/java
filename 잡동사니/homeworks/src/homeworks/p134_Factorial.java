package homeworks;

import java.util.Scanner;

public class p134_Factorial {

	public static void main(String[] args) {
		long fact=1;
		int n=0;
		System.out.print("������ �Է��Ͻÿ� : ");
		Scanner scan = new Scanner(System.in);
		n=Integer.parseInt(scan.nextLine());
		for(int i=1; i<=n; i++) {
			fact*=i;
		}
		System.out.printf("%d!�� %d�Դϴ�.",n, fact);

	}

}
