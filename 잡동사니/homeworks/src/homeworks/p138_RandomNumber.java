package homeworks;
public class p138_RandomNumber {
	public static void main(String[]args){
		double value = 0.0;
		int num=0;
		value = Math.random();					//Ŭ������ ������ �� �ִ� �� ������ ��������Լ�(Math->�빮��)
		System.out.println("������ = "+value);		//���ϰ��� double�̶� �Ǽ���� 0~0.999...
		num=(int)(value*=(100-50+1)+50);		//(�ִ밪-�ּҰ�+1)+�ּҰ� = ���ϴ� ������ ��
		System.out.println("������ = "+num);
	}
}