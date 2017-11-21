import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;


public class ObjectStreamTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("c:/temp/object.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(new Date());
		oos.close();
		fos.close();
		FileInputStream fis = new FileInputStream("c:/temp/object.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Date d = (Date)ois.readObject();
		System.out.println(d);
		ois.close();
		fis.close();
		
		FileOutputStream foss = new FileOutputStream("c:/temp/Student.dat");
		ObjectOutputStream ooss = new ObjectOutputStream(foss);
		ooss.writeObject(new ObjectStreamClass("이름이름",1234));
		ooss.close();
		foss.close();
		FileInputStream fiss = new FileInputStream("c:/temp/Student.dat");
		ObjectInputStream oiss = new ObjectInputStream(fiss);
		ObjectStreamClass dd = (ObjectStreamClass)oiss.readObject();
		System.out.println(dd);
		oiss.close();
		fiss.close();
		
		
		
	}
}