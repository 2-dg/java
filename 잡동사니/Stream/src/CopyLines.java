import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyLines {

	public static void main(String[] args) throws IOException {
		FileReader input = new FileReader("C:/temp/input.txt");		
		BufferedReader in = new BufferedReader(input);
		FileWriter output = new FileWriter("C:/temp/output.txt");		
		FileWriter output1 = new FileWriter("C:/temp/output1.txt");		
		BufferedWriter out = new BufferedWriter(output);
		BufferedWriter out1 = new BufferedWriter(output1);
		
		int c;
		while((c=in.read())!=-1) {
			out.write(c);
		}
		// È¤Àº
		String d;
		while((d=in.readLine())!=null) {
			out1.write(d);
			out1.newLine();
		}
		
		
		
		in.close();
		input.close();
		out.close();
		output.close();
		
		
	}

}
