package homeworks;

import java.util.Scanner;

public class p141_Averager {

	public static void main(String[] args) {
		int total=0, count=0;
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("점수를 입력하시오 : ");
			int grade=Integer.parseInt(scan.nextLine());
			if(grade<0)break;
			
			total += grade;
			count++;
		}
		System.out.println("평균은 "+total/count);

	}

}
