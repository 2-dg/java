import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Translator extends Thread{
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
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
			
			out.println("���� "+clientID);
			out.print("�ܾ� �Է� : ");
			//�������� 2���� �޼��� ����. Ŭ���̾�Ʈ���� 1���� �޼��� ����. �������� 1���� �޼��� ����.
			//Ŭ���̾�Ʈ������ 2���� ����. 1���� ����. 1���� ���� ������ ���ľ� ��.
			//�̰� Ŭ���̾�Ʈ�� �������� ���������̶�� �ϴ� ����.
			while(true) {
				String input = in.readLine();
				//client�� �Է��� ��ٸ��� blocking����
				if(input==null) {
					break;
				}else if(input.equals("JAVA")||input.equals("java")) {
					out.println("�� : �ڹ�");
				}else {
					out.println("���� �ܾ���");
				}
			}//end of while
			in.close();
			out.close();
			InetAddress iad = socket.getInetAddress();
			System.out.println("���� ����"+iad.getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
