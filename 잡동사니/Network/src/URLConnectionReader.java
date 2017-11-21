import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.nate.com:80");
		URLConnection conn = url.openConnection();
		//new File("http://www.naver.com:80/main.jsp");		
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF8"); //html은 UTF8방식임.
		BufferedReader br = new BufferedReader(isr);

		FileOutputStream fos = new FileOutputStream("c:/temp/이동규.html");
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		String str;
		while((str=br.readLine())!=null) {
			System.out.println(str);
			bw.write(str);
		}
								
		br.close();
		isr.close();
		is.close();
		bw.close();
		osw.close();
		fos.close();
		
	}
}