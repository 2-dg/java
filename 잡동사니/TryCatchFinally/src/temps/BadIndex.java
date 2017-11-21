package temps;

public class BadIndex {
	public static final int SIZE = 10;

	public static void main(String[] args) {
		int[] array = new int[SIZE];
		for (int i = 0; i < 10; i++) {
			array[i] = i + 1;
			System.out.println(array[i]);
		}
		System.out.println("=====���м�=====");
		for (int a : array) {
			System.out.println(a);
		}
		try {
			System.out.println(array[9]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�����Դϴ�.");
		} finally {
			System.out.println("������");
		}

		try {
			System.out.println(array[12]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�����Դϴ�.");
			// e.printStackTrace(); //�����޼��� ���
			return;
		} catch (Exception e) {
			System.out.println("����");
		} // Exception�� �ֻ��� ����ó�� Ŭ�����̹Ƿ�
			// �������� �־�� ��
		finally {
			System.out.println("������"); // finally�� return�� �൵ ������
		}
		System.out.println("��"); // �����̸� �̰� ���� ����
	}
}