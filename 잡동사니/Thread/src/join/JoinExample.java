package join;

public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		try {
			sumThread.join();						//�̷��� �ϸ� sumThread�� �������� ���� mainThread�� ����
													//�̰� �̿��ϸ� �����带 1~10������ �ùٸ��� ���� �� ������
		} catch (InterruptedException e) {
		}
		//Ʈ���� ĳġ�κ��� �ּ�ó���ϸ� ������ 0�� ��µǴµ� �� ������ ���ΰ��踦 �������� ������
		//CPU �����췯�� �����ϰ� ���ν������ ���������� �켱������ �����ϱ� ������.
		System.out.println("1~100 ��: " + sumThread.getSum());
		System.out.println("���ν����� ����");
	}
}

