package examples2;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[]args) {
		//calendar�� �̱��������� ��ü�� �����ؾ� ��.
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
		
	}

}
