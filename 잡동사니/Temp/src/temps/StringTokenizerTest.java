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
		// �̷��� ��ü�� ���� ���� tok�ȿ��� ��ū���� �з��� �ܾ��� ������ ���Ե�(6��)
		while (tok.hasMoreTokens()) { // �� ������ True, ������ False
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