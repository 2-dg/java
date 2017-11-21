package examples;

import java.util.Scanner;

public class BinaryConverter {
	public static void main(String[]args) {
		int value=0;
		System.out.print("정수 : ");
		Scanner input = new Scanner(System.in);
		value=Integer.parseInt(input.nextLine());
		String str=toBinaryString(value); //리턴값 : 문자열
		System.out.println("정수값 : " +value+ " = " +str);
		/*자바는 클래스를 불러와서 객체로서 기능을 수행시키기 때문에 쓰지 않는 기능까지
		  붙여 넣으므로 메모리의 소모가 많다. 그래서 작동이 느린 것.*/		
	}	
	public static String toBinaryString(int value){		
		String str=Integer.toBinaryString(value);
		for(;str.length()<32;){
			str="0"+str;
		} //str.length는  문자열의 길이를 의미하므로 바뀐 2진수의 자리수가 될 때까지
		  //앞에 0을 붙이는 연산을 계속 수행하게 하는 것이다.
		return str;
	}
}