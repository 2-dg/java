public class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}

	public void NotFoundException() {
		System.out.println("NotFoundException �����Դϴ�.");
		this.printStackTrace();
	}

	public void NegativeBalanceException() {
		System.out.println("NegativeBalanceException ���� �߻��Դϴ�.");
		this.printStackTrace();
	}
}