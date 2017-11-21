package trainning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Translator implements Runnable{
	private Socket socket;
	private int clientID;
	
	public Translator(Socket socket, int clientID) {
		super();
		this.socket = socket;
		this.clientID = clientID;
	}
	
	@Override
	public void run() {
		try {
			HashMap<String, String> dic = new HashMap<String, String>();
			dic.put("JAVA", "자바");
			dic.put("C", "씨");
			dic.put("php", "피에이치피");
			dic.put("note", "노트");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
			
			System.out.println(socket.getRemoteSocketAddress()+"가 서버에 접속하였습니다.");
			System.out.println("해당된 클라이언트 아이디는 "+clientID+"입니다.");
			
			out.println("서버에 접속하였습니다. 당신에게 해당된 아이디는 "+clientID+"입니다.");
			
			while(true) {
				String search = in.readLine();
				out.println(dic.get(search));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}


