package homeworks;

import java.util.Scanner;

public class p130_Gcd {
	public static void main(String[]args) {
		int x=0,y=0,r=0;
		System.out.print("�ΰ��� ������ �Է��Ͻÿ�(ū��, ������) : ");
		Scanner scan = new Scanner(System.in);
		x=Integer.parseInt(scan.nextLine());
		y=Integer.parseInt(scan.nextLine());
		//�̷��� �ϸ� ���ͷ� �޾ƾ��ϰ�, nextInt�� ������ �����̽��ٷ� �޾Ƶ� ��.
		while(y != 0) {
			r=x%y;
			x=y;
			y=r;
		}
		System.out.println("�ִ� ������� "+x);		
	}
}