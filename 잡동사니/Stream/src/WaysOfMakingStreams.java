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
		FileInputStream input = new FileInputStream(file);			//1단계
		BufferedInputStream bi = new BufferedInputStream(input);	//2단계
		DataInputStream dis = new DataInputStream(bi);				//3단계
		
		//가장 잘 쓰는 방법
		InputStreamReader isr = new InputStreamReader(input);		//2-2단계
		BufferedReader br = new BufferedReader(isr);				//2-3단계	
		
		
		String str;
		while((str = br.readLine())!=null) {
			System.out.println(str);
		}
		
//		DataInputStream방법(3단계)
//		String str;
//		while((str=dis.readLine())!=null) {				
//			System.out.println(str);
//		}
//		dis.close();
//		//JAVA가 2바이트체계이므로 선호하지 않으므로 빨간 줄이 뜸
		
//		FileInputStream방법(1단계)		
//		int c = 0;
//		while ((c = input.read()) != -1) {
//			// System.out.println(c); //그냥 int c하면 ASCII값으로 출력
//			System.out.print((char) c); // 이렇게 해야 멀쩡하게 출력됨
//		}//속도가 바이트단위->2바이트이기 때문에 빠르진 않음.
//		
//		BufferdInputStream방법(2단계)
//		byte[] b = new byte[255];
//		int count;
//		while((count = bi.read(b))!=-1) {
//			String str = new String(b,0,count);
//			System.out.println("바이트");
//			System.out.print(str);
//			System.out.println("바이트");
//		}
//		count=0;
//		while((count = bi.read(b))!=-1) {
//			String str = new String(b,0,count);
//			System.out.println("바이트");
//			System.out.print(str);
//			System.out.println("바이트");
//		}
		//스트림은 한 번 읽고 나면 커서가 거기에 위치해 있으므로 한번 출력하면 다시 출력이 안 됨.
		//clear라는 명령어를 통해 그 커서 위치를 초기화시켜줘야 함.
		
	
	}
}