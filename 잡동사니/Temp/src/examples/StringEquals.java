package examples;

import java.util.Scanner;

public class StringEquals {

	public static void main(String[] args) {
		String str;
		Scanner scan = new Scanner(System.in);
		for (;;) {
			System.out.print("문자열 입력 : ");
			str = scan.nextLine();
			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			String str1 = str.substring(0, 4);
			if (str1.equals("www.")) {
				System.out.println("www.으로 시작합니다.");
			} else {
				System.out.println("www.으로 시작하지 않습니다.");
			}
		}
	}

}
