package examples;

import java.util.Scanner;

public class StringEquals {

	public static void main(String[] args) {
		String str;
		Scanner scan = new Scanner(System.in);
		for (;;) {
			System.out.print("���ڿ� �Է� : ");
			str = scan.nextLine();
			if (str.equals("exit")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			String str1 = str.substring(0, 4);
			if (str1.equals("www.")) {
				System.out.println("www.���� �����մϴ�.");
			} else {
				System.out.println("www.���� �������� �ʽ��ϴ�.");
			}
		}
	}

}
