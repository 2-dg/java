package examples;

import java.util.Scanner;

public class BinaryConverter {
	public static void main(String[]args) {
		int value=0;
		System.out.print("���� : ");
		Scanner input = new Scanner(System.in);
		value=Integer.parseInt(input.nextLine());
		String str=toBinaryString(value); //���ϰ� : ���ڿ�
		System.out.println("������ : " +value+ " = " +str);
		/*�ڹٴ� Ŭ������ �ҷ��ͼ� ��ü�μ� ����� �����Ű�� ������ ���� �ʴ� ��ɱ���
		  �ٿ� �����Ƿ� �޸��� �Ҹ� ����. �׷��� �۵��� ���� ��.*/		
	}	
	public static String toBinaryString(int value){		
		String str=Integer.toBinaryString(value);
		for(;str.length()<32;){
			str="0"+str;
		} //str.length��  ���ڿ��� ���̸� �ǹ��ϹǷ� �ٲ� 2������ �ڸ����� �� ������
		  //�տ� 0�� ���̴� ������ ��� �����ϰ� �ϴ� ���̴�.
		return str;
	}
}