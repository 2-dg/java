import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaysOfMakingStreams {

	public static void main(String[] args) throws IOException {
		File file = new File("c:/temp/input.txt");
		FileInputStream input = new FileInputStream(file);			//1�ܰ�
		BufferedInputStream bi = new BufferedInputStream(input);	//2�ܰ�
		DataInputStream dis = new DataInputStream(bi);				//3�ܰ�
		
		//���� �� ���� ���
		InputStreamReader isr = new InputStreamReader(input);		//2-2�ܰ�
		BufferedReader br = new BufferedReader(isr);				//2-3�ܰ�	
		
		
		String str;
		while((str = br.readLine())!=null) {
			System.out.println(str);
		}
		
//		DataInputStream���(3�ܰ�)
//		String str;
//		while((str=dis.readLine())!=null) {				
//			System.out.println(str);
//		}
//		dis.close();
//		//JAVA�� 2����Ʈü���̹Ƿ� ��ȣ���� �����Ƿ� ���� ���� ��
		
//		FileInputStream���(1�ܰ�)		
//		int c = 0;
//		while ((c = input.read()) != -1) {
//			// System.out.println(c); //�׳� int c�ϸ� ASCII������ ���
//			System.out.print((char) c); // �̷��� �ؾ� �����ϰ� ��µ�
//		}//�ӵ��� ����Ʈ����->2����Ʈ�̱� ������ ������ ����.
//		
//		BufferdInputStream���(2�ܰ�)
//		byte[] b = new byte[255];
//		int count;
//		while((count = bi.read(b))!=-1) {
//			String str = new String(b,0,count);
//			System.out.println("����Ʈ");
//			System.out.print(str);
//			System.out.println("����Ʈ");
//		}
//		count=0;
//		while((count = bi.read(b))!=-1) {
//			String str = new String(b,0,count);
//			System.out.println("����Ʈ");
//			System.out.print(str);
//			System.out.println("����Ʈ");
//		}
		//��Ʈ���� �� �� �а� ���� Ŀ���� �ű⿡ ��ġ�� �����Ƿ� �ѹ� ����ϸ� �ٽ� ����� �� ��.
		//clear��� ��ɾ ���� �� Ŀ�� ��ġ�� �ʱ�ȭ������� ��.
		
	
	}
}