package homeworks;

import java.util.Scanner;

public class p110_Gradling {
	public static void main(String[]args) {
		int score=0;
		Scanner input = new Scanner(System.in);
		System.out.print("성적을 입력하시오 : ");
		score=Integer.parseInt(input.nextLine());
		if(score>=90){
			System.out.print("학점 A");
		}else if(score>=80){
			System.out.print("학점 B");
		}else if(score>=70){
			System.out.print("학점 C");
		}else if(score>=60){
			System.out.print("학점 D");
		}else System.out.print("학점 F");	
	}
}