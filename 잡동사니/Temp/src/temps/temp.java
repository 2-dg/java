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
		out.writeUTF("��ȣ\t�̸�\t��ȭ��ȣ\t\t�̸����ּ�");
		System.out.println("��ȣ\t�̸�\t��ȭ��ȣ\t\t�̸����ּ�");
		System.out.print("�� ���� ������ �Է��Ͻðڽ��ϱ� : ");
		int count = Integer.parseInt(scan.nextLine());
		for(int i=0;i<count;i++) {
			System.out.print((i+1)+"��° �Է��Դϴ�. �̸��� �Է��ϼ��� : ");
			String name = scan.nextLine();
			System.out.print("��ȭ��ȣ�� �Է��ϼ��� : ");
			String pnum = scan.nextLine();
			System.out.print("�����ּҸ� �Է��ϼ��� : ");
			String mail = scan.nextLine();
			out.writeUTF((i+1)+"\t"+name+"\t"+pnum+"\t\t"+mail);
		}
		
		
		in.close();
		out.close();
	}
}