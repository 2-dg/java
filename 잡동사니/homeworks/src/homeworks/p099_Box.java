package homeworks;

import java.util.Scanner;

public class p099_Box {
	public static void main(String[]args){
		double width = 0.0, height = 0.0, area = 0.0, perimeter = 0.0;
		Scanner input = new Scanner(System.in);
		System.out.print("사각형의 가로를 입력하시오 : ");
		width=Double.parseDouble(input.nextLine());
		System.out.print("사각형의 세로를 입력하시오 : ");
		height=Double.parseDouble(input.nextLine());
		area = width * height;
		perimeter = 2.0* (width + height);
		
		System.out.println("사각형의 넓이는 "+area);
		System.out.println("사각형의 둘레는 "+perimeter);
	}

}
