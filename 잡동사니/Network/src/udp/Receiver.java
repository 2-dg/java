package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {

	public static void main(String[] args) throws IOException {
		System.out.println("데이터 수신 대기");
		byte[] buf = new byte[256];
		DatagramSocket socket = new DatagramSocket(5000);
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		for(int i=0;i<10;i++) {
		socket.receive(packet);
		System.out.println(new String(buf));
		}
		System.out.println("데이터 수신 완료");

	}

}
