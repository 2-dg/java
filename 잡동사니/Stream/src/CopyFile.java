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
		//메인에서 해당 파일을 읽기만 하는 스트림을 놓음(Byte단위)
		OutputStream output = new FileOutputStream("C:/temp/output.txt");
		//메인에서 해당 파일을 작성하는 스트림을 놓음(Byte단위)
		int data=0;
		while((data = input.read())!=-1) {		//input.read()는 읽을 값이 없으면 -1을 리턴한다.
			str.append((char)data);			
			output.write(data);
		}
		System.out.println(str);
		input.close();
		output.close();
		
	}

}
