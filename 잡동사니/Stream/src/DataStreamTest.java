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
		//������ �ص� �ȴ�.
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		
		dos.writeDouble(3.14);
		dos.writeInt(100);
		dos.writeUTF("�����Ƹ��Ƹ��ƾƾ��������ٸ�");
		//Stream�迭�� ���ڵ��� �� �ǹǷ� �̷������� �����Ѵ� �ϴ���
		//����ڰ� �ν��� �� �ִ� ���·� ��������� �ʴ´�.
		
		dos.flush();
		dos.close(); //�̰� �ݾƾ� ��Ʈ�� ������ ���� ����.
					//�׷��� ��Ʈ�� ����� ������ ������ �����ϴ� ������ ������.
		System.out.println(dis.readDouble());
		System.out.println(dis.readInt());
		System.out.println(dis.readUTF());
		dis.close();
		
	}
}