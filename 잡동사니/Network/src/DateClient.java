import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DateClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.1.90", 9105);	//�������� ���ӿ�û ���� �� ���ϻ���(3��)
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String massage = br.readLine();
		System.out.println("�����κ��� ���۵� �޼��� : "+massage);
		System.out.print("Ŭ���̾�Ʈ ����.");
		br.close();
		isr.close();
		is.close();
		socket.close();
	}
}
