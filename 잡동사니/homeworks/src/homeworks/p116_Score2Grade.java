package homeworks;

import java.util.Scanner;

public class p116_Score2Grade {

	public static void main(String[] args) {
		int score=0,number=0;
		char grade=0;
		Scanner input = new Scanner(System.in);
		System.out.print("������ �Է��Ͻÿ� : ");
		score = Integer.parseInt(input.nextLine());
		number=score/10;
		switch(number) {
		case 10:
		case 9:grade='A'; break;
		case 8:grade='B'; break;
		case 7:grade='C'; break;
		case 6:grade='D'; break;
		default: grade='F'; break;		
		}
		System.out.println("���� : "+grade);

	}

}
