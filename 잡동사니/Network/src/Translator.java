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
			
			out.println("ㅎㅇ "+clientID);
			out.print("단어 입력 : ");
			//서버에서 2번의 메세지 전송. 클라이언트에서 1개의 메세지 수신. 서버에서 1개의 메세지 전송.
			//클라이언트에서는 2번의 수신. 1번의 전송. 1번의 수신 과정을 거쳐야 함.
			//이게 클라이언트와 서버간의 프로토콜이라고 하는 거임.
			while(true) {
				String input = in.readLine();
				//client의 입력을 기다리는 blocking상태
				if(input==null) {
					break;
				}else if(input.equals("JAVA")||input.equals("java")) {
					out.println("뜻 : 자바");
				}else {
					out.println("없는 단어임");
				}
			}//end of while
			in.close();
			out.close();
			InetAddress iad = socket.getInetAddress();
			System.out.println("접속 종료"+iad.getHostAddress());
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
