import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("time-c.nist.gov",13);
		Socket ss = new Socket("129.6.15.30",13);    //Client Socket
		InputStream is = s.getInputStream();
		byte[] b = new byte[1024];
		int data=0;
		while((data=is.read(b))!=-1) {
			String str = new String(b,0,data);
			System.out.println(str);
		}
		is.close();

	}
}