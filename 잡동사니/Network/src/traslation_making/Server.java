package traslation_making;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server {
	private static int clientID = 0;

	public static void main(String[] args) throws IOException {
		System.out.println("서버작동");
		ServerSocket ss = new ServerSocket();
		ss.bind(new InetSocketAddress("localhost", 9100));
		try {
			while (true) {
				clientID++;
				Translator t = new Translator(ss.accept(), clientID);
				t.start();
			}
		} finally {
			ss.close();
		}
	}
}