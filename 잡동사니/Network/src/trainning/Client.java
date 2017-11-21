package trainning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",9100);
		Scanner scan = new Scanner(System.in);
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(isr);
		
		OutputStream os = socket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		System.out.println(in.readLine());
		
		while(true) {
			System.out.print("찾을 단어를 입력하세요 : ");
			String word = scan.nextLine();
			out.println(word);
			System.out.println("찾는 단어의 의미 : "+in.readLine());
			
		}

	}

}
