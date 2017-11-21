package homeworks;

import java.util.Scanner;

public class p107_Bonus {
	public static void main(String[]args){
		final int targetSales = 1000;
		int mySales = 0, bonus = 0;
		String result;
		Scanner input = new Scanner(System.in);
		System.out.print("실적을 입력하시오(단위 : 만원) : ");
		mySales=Integer.parseInt(input.nextLine());
		
		if(mySales>=targetSales){
			result="실적달성";
			bonus=(mySales - targetSales)/10;
		}else{
			result="실적달성못함";
			bonus = 0;
		}

		System.out.print(result+"\n"+ "보너스" + bonus);
	}
		
}