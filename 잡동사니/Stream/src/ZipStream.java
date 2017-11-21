import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipStream {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("c:/temp/temp.zip");
		ZipInputStream zis = new ZipInputStream(fis);
		ZipEntry entry = null;
		while((entry = zis.getNextEntry())!=null) {
			System.out.println("압축 해제 : "+entry.getName());
			FileOutputStream fos = new FileOutputStream("c:/temp/"+entry.getName());
			
			int c = 0;
			while((c=zis.read())!=-1) {
				fos.write(c);
			}
			fos.close();
			
		}
		zis.close();
		
		
		
	}

}
