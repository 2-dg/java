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
		ss.bind(new InetSocketAddress("192.168.1.90", 9105)); //서버소켓을 만드는 과정 (1번)
		while(true) {
		System.out.println("클라이언트 접속 대기");
		Socket client_socket = ss.accept();			//접속대기 2번. 접속시 클라이언트소켓 생성. (4번)
		count++;			      					//클라이언트가 접속하기를 기다림
		System.out.println("클라이언트 접속 완료");
		OutputStream os = client_socket.getOutputStream();
		InetAddress ia = client_socket.getInetAddress();  //서버에 접속한 클라이언트 정보
		String clientAddress = ia.getHostAddress();							      //클라이언트 주소 출력
		PrintWriter pw = new PrintWriter(os, true);	//true->자동 flush기능 on
		pw.println(clientAddress+" : 서버에서 보내는 메세지입니다. 당신은 "+count+"번째로 서버에 방문해주셨습니다.");
		
		pw.close();
		os.close();
		client_socket.close();
		//ss.close();이건 닫으면 서버소켓이 닫히니 안 됨. 간단하게 생각해서 while문 안에 있는 애들만 닫자.
		
		
		}
	}
}