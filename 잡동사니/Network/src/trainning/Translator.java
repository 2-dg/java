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
			dic.put("JAVA", "�ڹ�");
			dic.put("C", "��");
			dic.put("php", "�ǿ���ġ��");
			dic.put("note", "��Ʈ");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
			
			System.out.println(socket.getRemoteSocketAddress()+"�� ������ �����Ͽ����ϴ�.");
			System.out.println("�ش�� Ŭ���̾�Ʈ ���̵�� "+clientID+"�Դϴ�.");
			
			out.println("������ �����Ͽ����ϴ�. ��ſ��� �ش�� ���̵�� "+clientID+"�Դϴ�.");
			
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


