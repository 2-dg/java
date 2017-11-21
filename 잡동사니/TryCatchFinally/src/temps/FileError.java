package temps;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileError {
	private static final int SIZE = 10;
	private int[] list;

	public FileError() {
		super();
		this.list = new int[SIZE];
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}
		writeList();
	}

	public void writeList() { // 파일처리는 오류처리를 강요함
		PrintWriter outStream = null;
		try {
			outStream = new PrintWriter(new FileWriter("outfile.txt"));
			for (int i = 0; i < SIZE; i++) {
				try {
					outStream.println("배열 요소" + i + " = " + list[i]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("오류");
				} catch (Exception e) {
					System.out.println("오류");
				} finally {
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException발생");
		} catch (IOException e) {
			System.out.println("IOException발생");
		} catch (Exception e) {
			System.out.println("Exception발생");
		} finally {
			if (outStream != null) {
				outStream.close();
			}
		}
	}
}