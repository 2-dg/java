package class_test;
import java.util.Scanner;
public class ClassMakingTestMain {
	public static void main(String[] args) {
		System.out.print("���� ��ü ���� �Է� : ");
		Scanner scan = new Scanner(System.in);
		int x=Integer.parseInt(scan.nextLine());
		Box[] check = new Box[x];							//�̷����ϸ� ��ü�� �����Ǵ� �� �ƴ϶� ��ü �迭�� ����.
															//�׷��� ��ü�� �ҷ��� �Լ��� �� �� ���� ��.
		for(int i=0;i<x;i++) {
			System.out.print("���� �Է� : ");
			int z= scan.nextInt();
		    //check[i].setRow(z);							//�׷��� �̷��� �ϸ� �Է��� �� ��. (��ü���ǰ� �� �����Ƿ�)
			System.out.print("���� �Է� : ");
			int y=scan.nextInt();
	        //check[i].setColumn(y);
			System.out.print("���� �Է� : ");
			int zz=scan.nextInt();
			//check[i].setHeight(zz);
			check[i] = new Box(z,y,zz);						//���⿡�� ��ü�� ���ǵǴ� ��.
			System.out.println("���Ǵ�"+check[i].cal_cube());
		}//�̰� ����
		System.out.print("���α׷� ����");
	}
}