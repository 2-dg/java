import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScanTest {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("c:/temp/output1.txt");
		PrintWriter pw = new PrintWriter(fw);
		pw.println("9.5");
		pw.println("567.5");
		pw.println("186.5");
		pw.flush();       				
		//printWriter에는 버퍼기능이 첨가돼있으므로 flush를 쓸 수 있다.
		//그러므로 굳이 BufferedWriter스트림을 놓을 필요 없다.
		pw.close();
		
		FileReader fr = new FileReader("c:/temp/output1.txt");
		FileReader fr2 = new FileReader("c:/temp/output2.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedReader br2 = new BufferedReader(fr2);
		//통상적인 방법
//		String str=null;
//		double sum = 0.0;
//		while((str=br.readLine())!=null){
//			sum+=Double.parseDouble(str);
//			System.out.println(str);
//		}
//		System.out.println("합계 : "+sum);
		
		//스캐너를 쓰는 방법
		double sum=0.0;
		Scanner scan = new Scanner(br);
		Scanner scan2 = new Scanner(br2);
		while(scan.hasNext()){
			if(scan.hasNextDouble()) {
				double value =scan.nextDouble();
				System.out.println(value);
				sum+=value;
			}else {
				scan.next();
			}
		}
		System.out.println("합계 : "+sum);
		br.close();
		scan2.useDelimiter(",");
		while(scan2.hasNext()){
			if(scan2.hasNextDouble()) {
				System.out.println(scan2.nextDouble());
			}else {
			}
		}
		
		
	}
}