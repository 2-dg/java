package traslation_making;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class Translator extends Thread {
	private Socket socket;
	private int userID;

	public Translator(Socket socket, int userID) {
		super();
		this.socket = socket;
		this.userID = userID;
	}

	@Override
	public void run() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter out = new PrintWriter(os, true);
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);

			HashMap<String, String> dic = new HashMap<String, String>();
			dic.put("JAVA", "자바");
			dic.put("C", "씨");
			dic.put("php", "피에이치피");
			dic.put("note", "노트");
			
			System.out.println(socket.getRemoteSocketAddress()+"가 서버에 접속");
			System.out.println("할당된 아이디 : " + userID);
			out.println("서버에 접속하였습니다. 당신의 클라이언트 ID는 " + userID + "입니다.");
			
			try {
			AAA:
			while (true) {
				out.println("단어를 입력하세요 (\"exit\"입력시 종료.) : ");
				String word = in.readLine();
				//System.out.println(word);
				if(word.equals("exit")) {
					break AAA;
				}
				out.println(dic.get(word));
			}
			}catch(java.net.SocketException e) {
				e.printStackTrace();
				System.out.println("오류 발생. 서버종료");
				in.close();
				isr.close();
				is.close();
				out.close();
				os.close();
			}finally {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println(userID+"번 사용자가 접속을 종료하였습니다.");
		}
	}
}