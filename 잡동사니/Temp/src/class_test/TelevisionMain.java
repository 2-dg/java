package class_test;

import java.util.Scanner;

public class TelevisionMain {

	public static void main(String[] args) {
		final int SIZE = 5;
		Television[] array = new Television[SIZE];		
		inputTV(array);
		printTV(array);
//		Scanner scan = new Scanner(System.in);		//스캐너 객체 생성
//		Television tel = new Television(15, 50, false);
//		System.out.println("채널 "+tel.getChannel());
//		//클래스를 만들면 Object로부터 자동상속돼서 그 안의 멤버함수를 쓸 수 있음
//		System.out.println("채널 "+tel.channel);//이렇게 하면 private이므로 참조가 불가능. 하지만 public으로 설정하면 가능.
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
			System.out.println((i+1)+"번째 객체 채널 : "+array[i].getChannel());
			System.out.println("볼륨 : "+array[i].getVolume());
			System.out.println("전원 : "+array[i].getOnoff());
			System.out.println();
		}
		System.out.println("============================");
		int i=0;
		for(Television value:array) {
			System.out.println((i+1)+"번째 객체 채널 : "+value.getChannel());
			System.out.println("볼륨 : "+value.getVolume());
			System.out.println("전원 : "+value.getOnoff());
			System.out.println();
			i++;
		}
		return;
	}
}