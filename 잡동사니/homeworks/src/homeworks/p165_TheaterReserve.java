package homeworks;

import java.util.Scanner;

public class p165_TheaterReserve {
	public static final int SIZE = 10;

	public static void main(String[] args) {
		int[] seats = new int[SIZE];
		Scanner scan = new Scanner(System.in);
		int a =seats.length;
		for (;;) {
			printForm();
			array(seats);
			System.out.print("\n원하시는 좌석번호를 입력하세요(종료는-1) : ");
			int x = Integer.parseInt(scan.nextLine());
			if (x == -1) {
				System.out.println("프로그램을 종료합니다.");
				return;
			} else if (seats[x-1] == 0) {
				seats[x - 1]++;
			} else {
				System.out.println("이미 예약된 자리입니다.");
			}
		}
	}

	public static void printForm() {
		System.out.println("=====================");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println("\n=====================");
	}

	public static void array(int[] seats) {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < SIZE; i++) {
			System.out.print((seats[i]) + " ");
		}
	}
}