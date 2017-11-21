package examples;

import java.util.Scanner;

public class ScannArrayLength {
	public static void main(String[] args) {
		int sum = 0;
		System.out.print("학생의 수를 입력하시오 : ");
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		int[] students = new int[num];
		for (int i = 0; i < students.length; i++) {
			System.out.print("학생" + (i + 1) + "의 성적 입력 : ");
			students[i] = Integer.parseInt(scan.nextLine());
			sum += students[i];
			if (students[i] < 0 || students[i] > 100) {
				System.out.println("잘못된 입력입니다. 다시입력하세요");
				sum-=students[i];
				i--;
			}
		}
		System.out.println("성적의 총 합계는" + sum + "평균은" + (double) sum / students.length);
	}

}
