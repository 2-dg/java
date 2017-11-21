package temps;

import java.io.IOException;

public class IOExceptionTest {
	public static final int SIZE = 100;

	public static void main(String[] args) throws IOException {
		String string = readString();

	}

	public static String readString() /* throws IOException */ {
		byte[] buf = new byte[SIZE];
		System.out.print("문자열 입력 : ");
		// System.in.read(buf);
		try {
			throw new IOException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(buf);
	}

}
