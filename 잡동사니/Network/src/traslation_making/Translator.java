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
			dic.put("JAVA", "�ڹ�");
			dic.put("C", "��");
			dic.put("php", "�ǿ���ġ��");
			dic.put("note", "��Ʈ");
			
			System.out.println(socket.getRemoteSocketAddress()+"�� ������ ����");
			System.out.println("�Ҵ�� ���̵� : " + userID);
			out.println("������ �����Ͽ����ϴ�. ����� Ŭ���̾�Ʈ ID�� " + userID + "�Դϴ�.");
			
			try {
			AAA:
			while (true) {
				out.println("�ܾ �Է��ϼ��� (\"exit\"�Է½� ����.) : ");
				String word = in.readLine();
				//System.out.println(word);
				if(word.equals("exit")) {
					break AAA;
				}
				out.println(dic.get(word));
			}
			}catch(java.net.SocketException e) {
				e.printStackTrace();
				System.out.println("���� �߻�. ��������");
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
			System.out.println(userID+"�� ����ڰ� ������ �����Ͽ����ϴ�.");
		}
	}
}