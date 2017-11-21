package homeworks;

public class p142_ContinueTest {

	public static void main(String[] args) {
		String s= "no news is good news";
		int n=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) != 'n') continue;
			n++;
		}
		System.out.println("문장에서 발견된 n의 개수 "+n);

	}

}
