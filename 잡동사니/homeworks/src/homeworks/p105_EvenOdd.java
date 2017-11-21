package homeworks;

import java.util.Scanner;

public class p105_EvenOdd {

	public static void main(String[] args) {
		int number = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("정수를 입력하시오 : ");
		number = Integer.parseInt(input.nextLine());
		if(number%2==0){
			System.out.println("입력된 정수는 짝수입니다.");
			}else{
			System.out.println("입력된 정수는 홀수입니다.");
			}
		System.out.println("프로그램이 종료되었습니다.");
	}
}
