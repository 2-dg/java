package polymorphism;

public class Driver {
	//DriverŬ������ Vehicle�������̽��� �з����ͷ� �ϴ� drive��� �޼ҵ带 
	//�����Ͽ��� �ش� �������̽��� �����Ͽ����� run();�̶�� �޼ҵ�� �� �����ؾ� �ϱ� ������
	//Driver�� drive�� �ϸ� Vehicle�� run�ϰ� �ǰ� ���� ���̴�.
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
}