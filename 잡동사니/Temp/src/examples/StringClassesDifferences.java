package examples;

public class StringClassesDifferences {
	public static void main(String[] args) {
		String str = "abcdefghijklmn";
		String str1 = "abcdefghijklmn"; //내부 값이 같으면 힙영역에 객체를 하나만 만든다(abc~)
		String str3 = "abcdefghijklmn"; //내부 값이 같으면 힙영역에 객체를 하나만 만든다(abc~)
		String str4 = "abcdefghijklmn"; //내부 값이 같으면 힙영역에 객체를 하나만 만든다(abc~)
		String str5 = "abcdefghijklmn"; //내부 값이 같으면 힙영역에 객체를 하나만 만든다(abc~)
		
		String str2 = new String("abcdefghijklmn");
		//이게 원형임. 대신 이렇게 하면 위에처럼 똑같은 객체를 가리키게 되는 게 아니라 하나를 더 만든다. 그러니까 new가 붙는 것.
		System.out.println(str2.charAt(1));
		//객체참조변수이므로 배열처럼 값을 읽어올 수 있다.
		for(int i=0;i<str.length();i++) {
			System.out.print(str2.charAt(i));
		}
	}
}
