package examples;

public class StringTest {

	public static void main(String[] args) {
		String proverb = new String ("A barking dog");
		String s1 = null, s2 = null, s3 = null, s4 = null;
		System.out.println(proverb+"�� ���ڿ��� ���̴� = "+proverb.length());
		s1 = proverb.concat(" never Bites!");
		System.out.println(s1+"�� ���̴� = "+s1.length());
		s2 = proverb.replace('b', 'B');
		System.out.println(s2+"�� ���̴� = "+s2.length());
		s3 = proverb.substring(2, 5);
		System.out.println(s3+"�� ���̴� = "+s3.length());
		s4 = proverb.toUpperCase();
		System.out.println(s4+"�� ���̴� = "+s4.length());
		int num=Integer.parseInt("123");
		System.out.println("Integer.parseInt(\"123\")+1="+(num+1));
		double dnum = Double.parseDouble("123.4");
		System.out.println("Double.parseDouble(\"123.4\")+1="+(dnum+1));
		float fnum = Float.parseFloat("123.4f");
		System.out.println("Double.parseDouble(\"123.4f\")+1.0f="+(fnum+1.0f));
		char cnum = proverb.charAt(0);					//���ڴ� �̷������� �Է¹޾ƾ� ��.
		System.out.println("cnum = "+cnum);
		boolean bnum = Boolean.parseBoolean("false");
		System.out.println("bnum = "+bnum);
		System.out.println("!bnum = "+!bnum);
		//���ڿ��� �ٲٱ�
		System.out.println(fnum+"");
		String str = String.valueOf(false);
		System.out.println(str + 1);
	}
}