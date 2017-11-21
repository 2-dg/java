package homeworks;

import java.util.Scanner;

public class p125_LoopExample2 {
	public static void main(String[]args) {
		int n=0,i=1;
		System.out.print("구구단 중에서 출력하고 싶은 단을 입력하시오 : ");
		Scanner scan = new Scanner(System.in);
		n=Integer.parseInt(scan.nextLine());
		while(i<=9) {
			System.out.println(n+"*"+i+"="+n*i);
			i++;
		}
	}

}
