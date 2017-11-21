package temps;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class temp {
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("c:/temp/user.txt");
		DataOutputStream out = new DataOutputStream(fos);
		FileInputStream fis = new FileInputStream("c:/temp/user.txt");
		DataInputStream in = new DataInputStream(fis);
		out.writeUTF("번호\t이름\t전화번호\t\t이메일주소");
		System.out.println("번호\t이름\t전화번호\t\t이메일주소");
		System.out.print("몇 개의 정보를 입력하시겠습니까 : ");
		int count = Integer.parseInt(scan.nextLine());
		for(int i=0;i<count;i++) {
			System.out.print((i+1)+"번째 입력입니다. 이름을 입력하세요 : ");
			String name = scan.nextLine();
			System.out.print("전화번호를 입력하세요 : ");
			String pnum = scan.nextLine();
			System.out.print("메일주소를 입력하세요 : ");
			String mail = scan.nextLine();
			out.writeUTF((i+1)+"\t"+name+"\t"+pnum+"\t\t"+mail);
		}
		
		
		in.close();
		out.close();
	}
}