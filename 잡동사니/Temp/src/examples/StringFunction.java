package examples;

import java.util.Scanner;

public class StringFunction {
	public static void main(String[]args) { //�׷� �̰� main�Լ����� ���ڿ� �迭 args�� ���ڴٴ��ǹ�. 
		                                    //�̸��� �ٲ㵵 ��. ����� arguments�� �ǹ��ϴ� args�� ��.
		int[] s = new int[] {0,10,20,30,50,6,7,8,9};
		int num=0;
		System.out.println("Ž���� ���� �Է��ϼ��� : ");
		Scanner scan = new Scanner(System.in);
		num=scan.nextInt();
		searchArray(s,num);
		}
	public static void searchArray(int[] s, int num) {
		for(int i=0;i<s.length;i++) {
			if(s[i]==num) {
				System.out.println("����� ã���� �ϴ� ��ġ��" +i);
				break;
			}
		}
	}
}
