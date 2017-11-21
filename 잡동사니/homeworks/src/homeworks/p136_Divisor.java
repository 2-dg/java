package homeworks;

import java.util.Scanner;

public class p136_Divisor {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("양의 정수를 입력하시오 : ");
		int n=Integer.parseInt(scan.nextLine());
		System.out.println(n+"의 약수는 다음과 같습니다.");
		for(int i=1; i<=n; ++i) {
			if(n%i==0) {
				System.out.print(i+" ");
			}
		}
	}
}