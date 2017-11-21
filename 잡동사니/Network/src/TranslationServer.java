import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TranslationServer {
	public static int clientID = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket();
		ss.bind(new InetSocketAddress("192.168.1.90", 9100));
		try {
			while (true) {
				System.out.println("Ŭ���̾�Ʈ ���� ���");
				Socket socket = ss.accept();			//blocking���·� ���� ���
				InetAddress iads = socket.getInetAddress();
				System.out.println("������ ���� �Ϸ�."+iads.getHostAddress());
				clientID++;
				Translator t = new Translator(socket, clientID); //Ŭ���̾�Ʈ �����尴ü ����
				t.start();
			}
		} finally {
			ss.close();  //���� �߻��� �������� �ݱ�
		}

	}

}
