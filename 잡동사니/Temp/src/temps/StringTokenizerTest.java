package temps;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StringTokenizerTest {
	public static void main(String[] args) {
		String str = "aser as wf asf f efs";
		String[] sArray = str.split(" ");
		String[] seeee = new String[50];
		for (int i = 0; i < sArray.length; i++) {
			System.out.println(sArray[i]);
		}
		System.out.println();
		for (String s : sArray) {
			System.out.println(s);
		}
		System.out.println();
		StringTokenizer tok = new StringTokenizer(str, " ");
		// 이렇게 객체를 만든 순간 tok안에는 토큰별로 분류한 단어의 갯수가 포함됨(6개)
		while (tok.hasMoreTokens()) { // 더 있으면 True, 없으면 False
			System.out.println(tok.nextToken());
		}

		StringTokenizer tok2 = new StringTokenizer(str, " ");
		System.out.println("=============");

		for (int i = 0; tok2.hasMoreTokens(); i++) {
			seeee[i] = tok2.nextToken();
			System.out.println(seeee[i]);
		}

	}

}