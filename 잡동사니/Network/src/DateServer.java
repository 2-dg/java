import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer {
public static int count =0;
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket();
		ss.bind(new InetSocketAddress("192.168.1.90", 9105)); //���������� ����� ���� (1��)
		while(true) {
		System.out.println("Ŭ���̾�Ʈ ���� ���");
		Socket client_socket = ss.accept();			//���Ӵ�� 2��. ���ӽ� Ŭ���̾�Ʈ���� ����. (4��)
		count++;			      					//Ŭ���̾�Ʈ�� �����ϱ⸦ ��ٸ�
		System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�");
		OutputStream os = client_socket.getOutputStream();
		InetAddress ia = client_socket.getInetAddress();  //������ ������ Ŭ���̾�Ʈ ����
		String clientAddress = ia.getHostAddress();							      //Ŭ���̾�Ʈ �ּ� ���
		PrintWriter pw = new PrintWriter(os, true);	//true->�ڵ� flush��� on
		pw.println(clientAddress+" : �������� ������ �޼����Դϴ�. ����� "+count+"��°�� ������ �湮���ּ̽��ϴ�.");
		
		pw.close();
		os.close();
		client_socket.close();
		//ss.close();�̰� ������ ���������� ������ �� ��. �����ϰ� �����ؼ� while�� �ȿ� �ִ� �ֵ鸸 ����.
		
		
		}
	}
}