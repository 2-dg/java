package examples;

import java.util.Scanner;

public class ScannArrayLength {
	public static void main(String[] args) {
		int sum = 0;
		System.out.print("�л��� ���� �Է��Ͻÿ� : ");
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		int[] students = new int[num];
		for (int i = 0; i < students.length; i++) {
			System.out.print("�л�" + (i + 1) + "�� ���� �Է� : ");
			students[i] = Integer.parseInt(scan.nextLine());
			sum += students[i];
			if (students[i] < 0 || students[i] > 100) {
				System.out.println("�߸��� �Է��Դϴ�. �ٽ��Է��ϼ���");
				sum-=students[i];
				i--;
			}
		}
		System.out.println("������ �� �հ��" + sum + "�����" + (double) sum / students.length);
	}

}
