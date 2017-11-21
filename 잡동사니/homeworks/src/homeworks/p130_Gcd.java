package homeworks;

import java.util.Scanner;

public class p130_Gcd {
	public static void main(String[]args) {
		int x=0,y=0,r=0;
		System.out.print("두개의 정수를 입력하시오(큰수, 작은수) : ");
		Scanner scan = new Scanner(System.in);
		x=Integer.parseInt(scan.nextLine());
		y=Integer.parseInt(scan.nextLine());
		//이렇게 하면 엔터로 받아야하고, nextInt로 받으면 스페이스바로 받아도 됨.
		while(y != 0) {
			r=x%y;
			x=y;
			y=r;
		}
		System.out.println("최대 공약수는 "+x);		
	}
}