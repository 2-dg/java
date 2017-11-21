import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CharEncodingTest {

	public static void main(String[] args) throws IOException {
		File file = new File("C:/temp/input.txt");
		FileInputStream input = new FileInputStream("C:/temp/input.txt");
		//FileInputStream input = new FileInputStream(file); �̰� ������
		InputStreamReader ir = new InputStreamReader(input,"UTF8");		
		BufferedReader in = new BufferedReader(ir);
		//������ BufferedInputStream���� ������ �Ǵµ� str�� �������� �� ������ ���ľ� ��
		//BufferedInputStream bis = new BufferedInputStream(input);
		String str;
		while((str=in.readLine())!=null) {
			System.out.println(str);
		}
		
		
		
		in.close();
		ir.close();
		input.close();

	}
}