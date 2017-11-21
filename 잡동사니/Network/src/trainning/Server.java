package trainning;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server {
	private static int clientID;
	public static void main(String[]args) throws IOException {
		ServerSocket ss = new ServerSocket();
		ss.bind(new InetSocketAddress("localhost", 9100));
		System.out.println("서버 정상 작동");
		while(true) {
			clientID++;
			Thread t = new Thread(new Translator(ss.accept(),clientID));
			t.start();
		}
	}

}
