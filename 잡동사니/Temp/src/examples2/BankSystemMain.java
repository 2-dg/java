package examples2;
import java.util.Scanner;
class BankSystemMain {
	public static void main(String[]args) {
		BankSystemClass f = new BankSystemClass(0.05,"123841-123123","���뿹��",0);
		Scanner scan = new Scanner(System.in);
		f.printAccount();
		for(;;) {
		System.out.print("������ �۾� ���� [1:�Ա�, 2:���, 3:�����Ⱓ ���ڰ��(�� ���� : "+f.getInterestRate()+"), 0:����] : ");
		int op = Integer.parseInt(scan.nextLine());
		if (op==0) {
			System.out.println("���α׷��� �����մϴ�.");
			return;
		}else if(op==1) {
			System.out.print("�Ա� �޾� �Է� : ");
			//int opm=scan.nextInt();						//�� ���⿡�� Integer.parseInt�� �ȵ���?
			int opm=Integer.parseInt(scan.nextLine());		//���� 10���� nextInt���µ� �ű� ���͹��۰� �����־
			f.deposit(opm);									//��������µƴ���
			System.out.print("�ܰ� ��� : ");
			System.out.println(f.getBalance());
		}else if(op==2) {
			System.out.print("��� �ݾ� �Է� : ");
			int opm=Integer.parseInt(scan.nextLine());
			f.withdraw(opm);
			System.out.print("�ܰ� ��� : ");
			System.out.println(f.getBalance());
		}else if(op==3) {
			System.out.print("���ڰ���� �����Ⱓ �Է�(��) : ");
			int opm=Integer.parseInt(scan.nextLine());
			f.calBalance(opm);
			System.out.print("�ܰ� ��� : ");
			System.out.println(f.getBalance());
		}
		}
	}
}
