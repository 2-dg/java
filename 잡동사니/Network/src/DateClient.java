import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DateClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.1.90", 9105);	//서버소켓 접속요청 진행 및 소켓생성(3번)
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String massage = br.readLine();
		System.out.println("서버로부터 전송된 메세지 : "+massage);
		System.out.print("클라이언트 종료.");
		br.close();
		isr.close();
		is.close();
		socket.close();
	}
}
