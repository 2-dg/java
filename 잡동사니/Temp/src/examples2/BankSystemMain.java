package examples2;
import java.util.Scanner;
class BankSystemMain {
	public static void main(String[]args) {
		BankSystemClass f = new BankSystemClass(0.05,"123841-123123","보통예금",0);
		Scanner scan = new Scanner(System.in);
		f.printAccount();
		for(;;) {
		System.out.print("실행할 작업 선택 [1:입금, 2:출금, 3:단위기간 이자계산(연 이율 : "+f.getInterestRate()+"), 0:종료] : ");
		int op = Integer.parseInt(scan.nextLine());
		if (op==0) {
			System.out.println("프로그램을 종료합니다.");
			return;
		}else if(op==1) {
			System.out.print("입금 급액 입력 : ");
			//int opm=scan.nextInt();						//왜 여기에선 Integer.parseInt가 안되지?
			int opm=Integer.parseInt(scan.nextLine());		//원래 10행이 nextInt였는데 거기 엔터버퍼가 남아있어서
			f.deposit(opm);									//오류가출력됐던것
			System.out.print("잔고 출력 : ");
			System.out.println(f.getBalance());
		}else if(op==2) {
			System.out.print("출금 금액 입력 : ");
			int opm=Integer.parseInt(scan.nextLine());
			f.withdraw(opm);
			System.out.print("잔고 출력 : ");
			System.out.println(f.getBalance());
		}else if(op==3) {
			System.out.print("이자계산할 단위기간 입력(연) : ");
			int opm=Integer.parseInt(scan.nextLine());
			f.calBalance(opm);
			System.out.print("잔고 출력 : ");
			System.out.println(f.getBalance());
		}
		}
	}
}
