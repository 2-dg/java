package singletone_test;

public class temp {
	private static temp t = null;
	private temp() {	//�����ڰ� private�̹Ƿ� ���������� ������ �Ұ�
	}					//�׷��� �������� ������ �Լ����� ��ü Ŭ������ ������Ű��
						//null�� �ƴ� �� �� ���� ���ϰ� ���� ��.
	public static temp getInstance() {
		if(t==null) {
			t= new temp();    //t�� null�����̸� ��ü�� ����� �ƴϸ� t�� �����Ѵ�.
			System.out.println("temp��ü ���� �Ϸ�");
		}					  //�̱�����(��ü�� �ϳ��� ����¹��)
		return t;
	}
}