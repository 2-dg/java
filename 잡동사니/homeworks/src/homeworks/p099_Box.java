package homeworks;

import java.util.Scanner;

public class p099_Box {
	public static void main(String[]args){
		double width = 0.0, height = 0.0, area = 0.0, perimeter = 0.0;
		Scanner input = new Scanner(System.in);
		System.out.print("�簢���� ���θ� �Է��Ͻÿ� : ");
		width=Double.parseDouble(input.nextLine());
		System.out.print("�簢���� ���θ� �Է��Ͻÿ� : ");
		height=Double.parseDouble(input.nextLine());
		area = width * height;
		perimeter = 2.0* (width + height);
		
		System.out.println("�簢���� ���̴� "+area);
		System.out.println("�簢���� �ѷ��� "+perimeter);
	}

}
