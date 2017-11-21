package class_test;

import java.util.Scanner;

public class CarMain {

	public static void main(String[] args) {
		Car car = new Car("black", 3, 80);
		System.out.println("색깔 : "+car.getColor());
		System.out.println("기어 : "+car.getGear());
		System.out.println("속도 : "+car.getSpeed());
		Scanner scan = new Scanner(System.in);
		final int SIZE = 10;
		int[] a = new int[SIZE];
		System.out.println("============");
//		inputArray(a); //입력받음
//		printArray(a); //출력함
		
		System.out.print("객체생성 개수 입력 : ");
		int x=Integer.parseInt(scan.nextLine());
		Car[] array = new Car[x];
		for(int i= 0; i<x; i++) {
			System.out.print("색깔 입력 : ");
			array[i].setColor(scan.nextLine());
			System.out.print("기어 입력 : ");
			array[i].setGear(scan.nextInt());
			System.out.print("속도 입력 : ");
			array[i].setSpeed(scan.nextInt());
		}
		for(int i= 0; i<x; i++) {
			System.out.print(array[i].getColor());
			System.out.print(array[i].getGear());
			System.out.print(array[i].getSpeed());
		}
		
	}//end of main
//	public static void inputArray(int[] a) {
//		for(int i=0;i<a.length;i++) {
//			a[i]=10*(i+1);
//		}
//		return;
//	}//end of inputArray
//	public static void printArray(int[] a) {
//		for(int i=0;i<a.length;i++) {
//			System.out.println("a["+i+"] = "+a[i]);
//		}
//		System.out.println("============");
//		int i=0;
//		for(int num: a) {
//			System.out.println("a["+i+"] = "+num);
//			i++;
//		}
//		return;
//	}//end of printArray
}