package homeworks;

import java.util.Scanner;

public class p117_StringSwitch {
	public static void main(String[]args) {
		int monthNumber=0;
		String month=null;
		Scanner input = new Scanner(System.in);
		System.out.print("월의 이름을 입력하시오 : ");
		month = input.nextLine();
		switch(month) {
		case "jan":monthNumber = 1; break;
		case "feb":monthNumber = 2; break;
		case "mar":monthNumber = 3; break;
		case "apr":monthNumber = 4; break;
		default: monthNumber=0;break;
		}
		System.out.println(monthNumber);
	}

}
