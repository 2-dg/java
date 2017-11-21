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
			System.out.print("\n���Ͻô� �¼���ȣ�� �Է��ϼ���(�����-1) : ");
			int x = Integer.parseInt(scan.nextLine());
			if (x == -1) {
				System.out.println("���α׷��� �����մϴ�.");
				return;
			} else if (seats[x-1] == 0) {
				seats[x - 1]++;
			} else {
				System.out.println("�̹� ����� �ڸ��Դϴ�.");
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