package class_test;
import java.util.Scanner;
public class ClassMakingTestMain {
	public static void main(String[] args) {
		System.out.print("만들 객체 수를 입력 : ");
		Scanner scan = new Scanner(System.in);
		int x=Integer.parseInt(scan.nextLine());
		Box[] check = new Box[x];							//이렇게하면 객체가 생성되는 게 아니라 객체 배열만 생성.
															//그래서 객체로 불러온 함수를 쓸 수 없는 것.
		for(int i=0;i<x;i++) {
			System.out.print("가로 입력 : ");
			int z= scan.nextInt();
		    //check[i].setRow(z);							//그래서 이렇게 하면 입력이 안 됨. (객체정의가 안 됐으므로)
			System.out.print("세로 입력 : ");
			int y=scan.nextInt();
	        //check[i].setColumn(y);
			System.out.print("높이 입력 : ");
			int zz=scan.nextInt();
			//check[i].setHeight(zz);
			check[i] = new Box(z,y,zz);						//여기에서 객체가 정의되는 것.
			System.out.println("부피는"+check[i].cal_cube());
		}//이게 정답
		System.out.print("프로그램 종료");
	}
}