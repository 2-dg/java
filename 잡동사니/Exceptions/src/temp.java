import java.util.Scanner;

public class temp {
	public static Scanner S = new Scanner(System.in);

	public static void main(String[] args) throws MyException {
		int[] array = new int[] { 0, 1, 2, 3, 4, 5 };
		try {
		searchArray(array);
		}catch(MyException e) {
			e.NotFoundException();
		}	
	}
	public static void searchArray(int[] array) throws MyException {
		int count = 0;
		System.out.print("ã�� ���ڸ� �Է��ϼ��� : ");
		int input = Integer.parseInt(S.nextLine());
		for (int i = 0; i < array.length; i++) {
			if (input == array[i]) {
				count++;
			}
		}
		if (count == 0) {
			throw new MyException("���� �߻��Դϴ�");
		} else {
			System.out.println("�����۵�");
		}
	}
}