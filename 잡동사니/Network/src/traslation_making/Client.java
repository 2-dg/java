package traslation_making;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scan = new Scanner(System.in);
		Socket s = new Socket("localhost",9100);
		
		OutputStream os = s.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(isr);
		
		System.out.println(in.readLine());
		while(true) {
		System.out.print(in.readLine());
		String user = scan.nextLine();
		if(user.equals("exit")) {
			System.out.println("프로그램을 종료합니다.");
			out.println(user);
			in.close();
			isr.close();
			is.close();
			out.close();
			os.close();
			s.close();
			System.exit(0);
		}
		out.println(user);
		String answer = in.readLine();
		System.out.println("찾는 단어의 의미 : "+answer);
		}
	}
}