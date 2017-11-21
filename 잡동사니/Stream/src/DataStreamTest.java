import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DataStreamTest {

	public static void main(String[] args) throws IOException {
		File file = new File("c:/temp/data.txt");
		FileOutputStream fos = new FileOutputStream(file);
		//FileOutputStream fos = new FileOutputStream("c:/temp/data.txt");
		//식으로 해도 된다.
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		
		dos.writeDouble(3.14);
		dos.writeInt(100);
		dos.writeUTF("아으아르아르아아아으동동다리");
		//Stream계열은 인코딩이 안 되므로 이런식으로 저장한다 하더라도
		//사용자가 인식할 수 있는 형태로 저장되지는 않는다.
		
		dos.flush();
		dos.close(); //이걸 닫아야 스트림 오류가 나지 않음.
					//그러니 스트림 사용이 끝나면 무조건 종료하는 습관을 들이자.
		System.out.println(dis.readDouble());
		System.out.println(dis.readInt());
		System.out.println(dis.readUTF());
		dis.close();
		
	}
}