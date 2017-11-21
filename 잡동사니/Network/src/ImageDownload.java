import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageDownload {
	
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.oracle.com/us/hp07-bg121314-openworld-2x-2280475.jpg");
		InputStream is = url.openStream();
		InputStream is2 = url.openStream();
		FileOutputStream fos = new FileOutputStream("c:/temp/ssss.jpg");
		FileOutputStream fos2 = new FileOutputStream("c:/temp/ssss2.jpg");
		byte[] b = new byte[1024];
		int c=0;
		//원래의 방법
		long start1 = System.nanoTime();
		while((c=is2.read())!=-1) {
			//System.out.println(c);
			fos2.write(c);
		}
		long end1 = System.nanoTime();
		
		c=0;
		//배열로 받는 것이 반복문을 적게 돌리므로 압도적으로 전송시간이 빠르다.
		long start = System.nanoTime();
		while((c=is.read(b))!=-1) {			//1024개에서 읽어온 바이트 갯수만 리턴
			//System.out.println(c);
			fos.write(b,0,c);				//저장된 b배열에서 0번째부터 c수까지만 읽어서 저장하겠다.
		}									//1024씩 끊으면 생기는 공백 때문에 이렇게 명령어를 해줘야 함.
		long end = System.nanoTime();
		end-=start;
		end1-=start1;
		System.out.println("배열방법 전송 완료. 완료 시간 : "+end);
		System.out.println("바이트 방법 전송 완료. 완료 시간 : "+end1);
		System.out.println("시간 차이 : "+(end1-end));
		
		is.close();
		fos.close();
	}
}