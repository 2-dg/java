import java.util.Scanner;

public class temp2 {
	static int balance = 0;
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws MyException {
		try {
			System.out.print("�Ա� �ݾ� �Է� : ");
			int money = Integer.parseInt(s.nextLine());
			deposit(money);
			System.out.println("����ݾ� : " + money);
			System.out.print("��� �ݾ� �Է� : ");
			money = Integer.parseInt(s.nextLine());
			witdraw(money);
		} catch (MyException e) {
			if (balance<0) {
			e.NegativeBalanceException();
			}else if (balance==0) {
				e.NotFoundException();
			}
		}
	}
	private static void witdraw(int money) throws MyException {
		balance -= money;
		if (balance < 0) {
			throw new MyException("�����߻�");
		}
	}
	private static void deposit(int money) throws MyException {
		balance += money;
		if (balance < 0) {
			throw new MyException("���� �߻�");
		}
	}
}