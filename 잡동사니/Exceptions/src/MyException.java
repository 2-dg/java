public class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}

	public void NotFoundException() {
		System.out.println("NotFoundException 오류입니다.");
		this.printStackTrace();
	}

	public void NegativeBalanceException() {
		System.out.println("NegativeBalanceException 오류 발생입니다.");
		this.printStackTrace();
	}
}