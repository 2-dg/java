package class_test;

public class MyMath extends Object {
	
	//public MyMath(){ }�� ����Ʈ ������. �ȸ���� �׳� �������ִٰ� ���� ��.
	@Override
	public String toString() {
		//return super.toString();
		return "����� toString Override�Դϴ�.";
		
	}													//�������̵� = �θ�� �Ȱ��ƾ� ��, �����ε��� ������ص� ��(�Ű����� ������ Ÿ�Ը�)
	public int square(int i) {
		return i*i;
	}
	public double square(double i) {
		return i*i;
	}
}