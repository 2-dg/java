package class_test;

import java.util.Scanner;

public class TelevisionMain {

	public static void main(String[] args) {
		final int SIZE = 5;
		Television[] array = new Television[SIZE];		
		inputTV(array);
		printTV(array);
//		Scanner scan = new Scanner(System.in);		//��ĳ�� ��ü ����
//		Television tel = new Television(15, 50, false);
//		System.out.println("ä�� "+tel.getChannel());
//		//Ŭ������ ����� Object�κ��� �ڵ���ӵż� �� ���� ����Լ��� �� �� ����
//		System.out.println("ä�� "+tel.channel);//�̷��� �ϸ� private�̹Ƿ� ������ �Ұ���. ������ public���� �����ϸ� ����.
//		tel.setChannel(30);
//		System.out.println(tel.getChannel());
	}//end of main
	public static void inputTV(Television[] array) {
		for(int i=0;i<array.length;i++) {
			array[i] = new Television((int)(Math.random()*16+5), 50, false);
		}
		return ;
	}
	public static void printTV(Television[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.println((i+1)+"��° ��ü ä�� : "+array[i].getChannel());
			System.out.println("���� : "+array[i].getVolume());
			System.out.println("���� : "+array[i].getOnoff());
			System.out.println();
		}
		System.out.println("============================");
		int i=0;
		for(Television value:array) {
			System.out.println((i+1)+"��° ��ü ä�� : "+value.getChannel());
			System.out.println("���� : "+value.getVolume());
			System.out.println("���� : "+value.getOnoff());
			System.out.println();
			i++;
		}
		return;
	}
}