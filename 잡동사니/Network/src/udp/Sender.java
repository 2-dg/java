package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {

	public static void main(String[] args) throws IOException, InterruptedException {
		DatagramSocket socket = new DatagramSocket();
		InetAddress address = InetAddress.getByName("127.0.0.1");
		System.out.println("데이터 전송 대기");
		for(int i=0;i<10;i++) {
		String s = "Hi im DG";
		System.out.println(s);
		byte[] buf = s.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 5000);
		socket.send(packet);
		Thread.sleep(500);
		}
		System.out.println("데이터 전송 완료");
	}

}
