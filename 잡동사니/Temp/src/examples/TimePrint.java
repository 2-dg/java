package examples;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePrint {

	public static void main(String[] args) {
		Date dt = new Date();
		System.out.println(dt.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a"); 
		System.out.println(sdf.format(dt).toString()); 
	}
}