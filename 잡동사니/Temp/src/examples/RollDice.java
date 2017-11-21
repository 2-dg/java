package examples;

public class RollDice {
	public static final int SIZE = 6;  		  //static�� ���� �ȿ��� ����. �ν��Ͻ��� �Ǳ� ����.
	public static final int COUNT = 10_000;	  //�������������� ����� ��밡��
	public final int SIZE2 = 6;				  //�̷��� �ν��Ͻ� ��������� ���������� ��ü�������� ������ ���� ����
	public static void main(String[]args) {
		int index=0;
		int[] freq = new int[RollDice.SIZE];  //�׳� SIZE�� �ᵵ ������ Ŭ���������� ���ٰ����ϱ� ������ ����
		//final int SIZE = 7; 				  //����VS���� �ߺ��Ǹ� ������ ��(�� �����). main�ȿ��� SIZE=7 		
											  //static�� ���� �ȿ��� ����. �ν��Ͻ��� �Ǳ� ����.
											  //������ �ڱ� ���̱� ������ ������ ������ ���̰� �ٸ� Ŭ�������� ������
											  //������ ����� ��.	
		RollDice roll = new RollDice();
		int a =10*roll.SIZE2;				  //�̷��� �ϸ�  a=10*6�� ��.
		
		for(int i=0;i<COUNT;i++) {
			index = (int)(Math.random()*6);
			freq[index]++;
		}		
		print_array(freq);
	}
	public static void print_array(int[] freq) {
		//new int[SIZE];                      // ���⿡���� SIZE�� 4���� 6��.
		for(int i=0;i< freq.length; i++) {
			System.out.println((i + 1) + "������ Ƚ���� " + freq[i]);
	}
	}
}
