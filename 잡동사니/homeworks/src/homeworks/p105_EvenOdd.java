package homeworks;

import java.util.Scanner;

public class p105_EvenOdd {

	public static void main(String[] args) {
		int number = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("������ �Է��Ͻÿ� : ");
		number = Integer.parseInt(input.nextLine());
		if(number%2==0){
			System.out.println("�Էµ� ������ ¦���Դϴ�.");
			}else{
			System.out.println("�Էµ� ������ Ȧ���Դϴ�.");
			}
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}
}
