package homeworks;

import java.util.Scanner;

public class p097_CircleArea {
	public static void main(String[]args){
		final double PI = 3.14;
		double radius = 0.0, area = 0.0;
		Scanner input = new Scanner(System.in);
		System.out.print("반지름을 입력하시오 : ");
		radius=Double.parseDouble(input.nextLine());
		area=PI*radius*radius;
		System.out.println(area);
	}
}