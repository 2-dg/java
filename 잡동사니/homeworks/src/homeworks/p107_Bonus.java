package homeworks;

import java.util.Scanner;

public class p107_Bonus {
	public static void main(String[]args){
		final int targetSales = 1000;
		int mySales = 0, bonus = 0;
		String result;
		Scanner input = new Scanner(System.in);
		System.out.print("������ �Է��Ͻÿ�(���� : ����) : ");
		mySales=Integer.parseInt(input.nextLine());
		
		if(mySales>=targetSales){
			result="�����޼�";
			bonus=(mySales - targetSales)/10;
		}else{
			result="�����޼�����";
			bonus = 0;
		}

		System.out.print(result+"\n"+ "���ʽ�" + bonus);
	}
		
}