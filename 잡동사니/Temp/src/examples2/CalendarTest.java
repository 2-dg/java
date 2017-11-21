package examples2;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[]args) {
		//calendar는 싱글톤방식으로 객체를 생성해야 함.
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
		
	}

}
