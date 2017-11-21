package examples;

import java.util.Scanner;

public class StringFunction {
	public static void main(String[]args) { //그럼 이건 main함수에서 문자열 배열 args를 쓰겠다는의미. 
		                                    //이름을 바꿔도 됨. 관행상 arguments를 의미하는 args를 씀.
		int[] s = new int[] {0,10,20,30,50,6,7,8,9};
		int num=0;
		System.out.println("탐색할 값을 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		num=scan.nextInt();
		searchArray(s,num);
		}
	public static void searchArray(int[] s, int num) {
		for(int i=0;i<s.length;i++) {
			if(s[i]==num) {
				System.out.println("당신이 찾고자 하는 위치는" +i);
				break;
			}
		}
	}
}
