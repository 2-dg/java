package homeworks;
import java.util.*;
public class p120_DaysInMonth {
	public static void main(String[]args) {
		int month=0, days=0;
		System.out.print("일수를 알고 싶은 월을 입력하시오 : ");
		Scanner scan = new Scanner(System.in);
		month = Integer.parseInt(scan.nextLine());
		switch(month) {
		case 4:
		case 6:
		case 9:
		case 11:
			days=30;break;
		case 2:
			days=28;break;
		default:
			days=31;break;		
		}
		System.out.println(month+"월의 일수는 "+days+"일입니다.");
	}
}


