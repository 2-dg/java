import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class HostIP {

	public static void main(String[] args) throws IOException {
		InetAddress address = InetAddress.getByName("www.naver.com");
		System.out.println("IP¡÷º“ : "+address.getHostAddress());
		
	}
}