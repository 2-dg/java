package examples;

public class StringClassesDifferences {
	public static void main(String[] args) {
		String str = "abcdefghijklmn";
		String str1 = "abcdefghijklmn"; //���� ���� ������ �������� ��ü�� �ϳ��� �����(abc~)
		String str3 = "abcdefghijklmn"; //���� ���� ������ �������� ��ü�� �ϳ��� �����(abc~)
		String str4 = "abcdefghijklmn"; //���� ���� ������ �������� ��ü�� �ϳ��� �����(abc~)
		String str5 = "abcdefghijklmn"; //���� ���� ������ �������� ��ü�� �ϳ��� �����(abc~)
		
		String str2 = new String("abcdefghijklmn");
		//�̰� ������. ��� �̷��� �ϸ� ����ó�� �Ȱ��� ��ü�� ����Ű�� �Ǵ� �� �ƴ϶� �ϳ��� �� �����. �׷��ϱ� new�� �ٴ� ��.
		System.out.println(str2.charAt(1));
		//��ü���������̹Ƿ� �迭ó�� ���� �о�� �� �ִ�.
		for(int i=0;i<str.length();i++) {
			System.out.print(str2.charAt(i));
		}
	}
}
