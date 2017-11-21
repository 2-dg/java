import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {

	public static void main(String[] args) throws IOException {
		StringBuffer str = new StringBuffer();
		InputStream input = new FileInputStream("C:/temp/input.txt");
		//���ο��� �ش� ������ �б⸸ �ϴ� ��Ʈ���� ����(Byte����)
		OutputStream output = new FileOutputStream("C:/temp/output.txt");
		//���ο��� �ش� ������ �ۼ��ϴ� ��Ʈ���� ����(Byte����)
		int data=0;
		while((data = input.read())!=-1) {		//input.read()�� ���� ���� ������ -1�� �����Ѵ�.
			str.append((char)data);			
			output.write(data);
		}
		System.out.println(str);
		input.close();
		output.close();
		
	}

}
