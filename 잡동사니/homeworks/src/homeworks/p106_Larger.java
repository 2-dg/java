package homeworks;

import java.util.Scanner;

public class p106_Larger {
	public static void main(String[]args){
	int x=0,y=0,max=0;
	Scanner input = new Scanner(System.in);
	System.out.print("ù��° ���� : ");
	x = Integer.parseInt(input.nextLine());
	System.out.print("�ι�° ���� : ");
	y = Integer.parseInt(input.nextLine());
	
	if(x>y) max = x;
	else max = y;
	System.out.println("ū ���� " +max);
	}
}
