package translation_test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class TranslationServer {

	public static void main(String[] args) throws Exception {
		System.out.println("���� ���� ������ �������Դϴ�.");
		int clientId = 0;
		ServerSocket ss = new ServerSocket();
		ss.bind(new InetSocketAddress("localhost", 9101));
		try {
			while (true) {
				clientId++;
				Socket socket = ss.accept();
				Translator t = new Translator(socket, clientId);
				t.start();
			}
		} finally {
			ss.close();
		}
	}

	private static class Translator extends Thread {
		private Socket socket;
		private int myId;

		public Translator(Socket socket, int clientId) {
			this.socket = socket;
			this.myId = clientId;
		}

		public void run() {
			HashMap<String,String> dic = new HashMap<String,String>();
			dic.put("java", "�ڹ�");
			dic.put("C", "��");
			dic.put("PHP", "�ǿ���ġ��");
			dic.put("note", "��Ʈ");
			dic.put("book", "å");			
			Set<String> key = dic.keySet();
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
				out.println("�ȳ��ϼ���? Ŭ���̾�Ʈ ��ȣ�� " + myId + "�Դϴ�.");
				while(true) {
				out.println("�ܾ �Է��ϼ���");
				String input = in.readLine();
				out.println(dic.get(input));
				}
				
				//println���� �ؾ���. ���ϸ� null���
				//System.out.println(dic.get(val));
//				while (true) {
//					String input = in.readLine();
//					if (input == null) {
//						break;
//					}
//					if (input.equals("java") == true)
//						out.println("java->�ڹ�");
//					else
//						out.println("���� ���� �ܾ �����ּ���.");
//				}
			} catch (IOException e) {
				System.out.println("Ŭ���̾�Ʈ ��ȣ: " + myId + "ó�� ����" + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("���� ���� ����" + e);
				}
				System.out.println("Ŭ���̾�Ʈ ��ȣ: " + myId + "ó�� ó�� ����");
			}
		}
	}
}