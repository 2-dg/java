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
		//������ ���
		long start1 = System.nanoTime();
		while((c=is2.read())!=-1) {
			//System.out.println(c);
			fos2.write(c);
		}
		long end1 = System.nanoTime();
		
		c=0;
		//�迭�� �޴� ���� �ݺ����� ���� �����Ƿ� �е������� ���۽ð��� ������.
		long start = System.nanoTime();
		while((c=is.read(b))!=-1) {			//1024������ �о�� ����Ʈ ������ ����
			//System.out.println(c);
			fos.write(b,0,c);				//����� b�迭���� 0��°���� c�������� �о �����ϰڴ�.
		}									//1024�� ������ ����� ���� ������ �̷��� ��ɾ ����� ��.
		long end = System.nanoTime();
		end-=start;
		end1-=start1;
		System.out.println("�迭��� ���� �Ϸ�. �Ϸ� �ð� : "+end);
		System.out.println("����Ʈ ��� ���� �Ϸ�. �Ϸ� �ð� : "+end1);
		System.out.println("�ð� ���� : "+(end1-end));
		
		is.close();
		fos.close();
	}
}