
public class CarExample {
	public static void main(String[] args) {
		//Tire�������̽��� ������ ����� HankookTire�� 4�� ������ Car��� ��ü�� ����
		Car myCar = new Car();
		//�� �ȿ� run();�̶�� �޼ҵ尡 �������ִµ�
		//�̴� �������ִ� Ÿ�̾������� Ȯ���ϴ� �޼ҵ�μ�
		//Tire�������̽��� �������ִ� roll();�� Override�� ������
		//���� �۵���Ű�� �޼ҵ�� ���� ����
		myCar.run();
		
		myCar.frontLeftTire = new KumhoTire();
		myCar.frontRightTire = new KumhoTire();
		//���� �������̽��� ��ӹ޾� ���� Ŭ�����̱� ������
		//TireŬ������ ������ Tire�� ���� ������ ������ ����
		myCar.run();
		//�ٽ� �������� �� Ÿ�̾ �ٲ����� �� �� ����.
		//Tire��� �������̽��� ��ȣŸ�̾�� �ѱ�Ÿ�̾ �԰�ȭ�߱� ������
		//��ü�� �����ϴٰ� �����ϸ� ����.
		
	}
}
