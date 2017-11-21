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

	public void writeList() { // ����ó���� ����ó���� ������
		PrintWriter outStream = null;
		try {
			outStream = new PrintWriter(new FileWriter("outfile.txt"));
			for (int i = 0; i < SIZE; i++) {
				try {
					outStream.println("�迭 ���" + i + " = " + list[i]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("����");
				} catch (Exception e) {
					System.out.println("����");
				} finally {
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException�߻�");
		} catch (IOException e) {
			System.out.println("IOException�߻�");
		} catch (Exception e) {
			System.out.println("Exception�߻�");
		} finally {
			if (outStream != null) {
				outStream.close();
			}
		}
	}
}