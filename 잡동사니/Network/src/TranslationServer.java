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
				System.out.println("클라이언트 접속 대기");
				Socket socket = ss.accept();			//blocking상태로 접속 대기
				InetAddress iads = socket.getInetAddress();
				System.out.println("서버에 접속 완료."+iads.getHostAddress());
				clientID++;
				Translator t = new Translator(socket, clientID); //클라이언트 스레드객체 생성
				t.start();
			}
		} finally {
			ss.close();  //문제 발생시 서버소켓 닫기
		}

	}

}
