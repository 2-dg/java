package homeworks;

import java.util.Scanner;

public class p106_Larger {
	public static void main(String[]args){
	int x=0,y=0,max=0;
	Scanner input = new Scanner(System.in);
	System.out.print("첫번째 정수 : ");
	x = Integer.parseInt(input.nextLine());
	System.out.print("두번째 정수 : ");
	y = Integer.parseInt(input.nextLine());
	
	if(x>y) max = x;
	else max = y;
	System.out.println("큰 수는 " +max);
	}
}
