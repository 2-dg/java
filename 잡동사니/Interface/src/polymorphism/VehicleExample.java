package polymorphism;

public class VehicleExample {
	public static void main(String[] args) {
		Vehicle vehicle = new Bus();
		vehicle.run();
		//vehicle.checkFare();
		//�� �޼ҵ�� ������ �� �ȴ�.
		//�� ������ �غ��ڸ� �� ���� Vehicle�̶�� ������
		//���� ������ Vehicle����� �޸𸮸� ��ڴٴ� �ǵ�
		//Bus Class�� ���� ���� Vehicle�������̽��� Ŭ������
		//Vehicle�� ������ ���ٰ� �ڽ��� ������ ���� �ִ� ����̴�.
		//������ vehicle�� Vehicle�� ���� �޸𸮸���̹Ƿ� Bus Class�� ���� �ִ�
		//CheckFare�޼ҵ带 �ν��� �� ����.
		//�̶��� �츮�� �Ǽ��� ������ �ٲٰų� �� �� ���� ����ȯ�� �ϸ� �ȴ�.
		Bus bus = (Bus) vehicle;
		//�ᱹ Bus��ŭ�� �޸𸮸� Ȯ�����ְ� �� �ȿ��ٰ�
		//Bus�� ����ȯ�� vehicle�� �־��ָ� �ȴ�.
		//�ƴ� �̷��� ��� �޸� �뷮�� vehicle��ŭ�� Ȯ���Ǵ���
		//Bus��ŭ�� �޼ҵ�� ������ ���ִ°ǰ�??
		bus.checkFare();

		Vehicle vehicle2 = new BusTest("�׽�Ʈ�ô�");
		vehicle2.run();
		//vehicle2.checkFare();
		//�ȵ��ڳ�
		BusTest busTest = (BusTest) vehicle2;
		busTest.checkFare();
		//üũ�غ��ϱ� ���ֳ�
		//�� ��ﳪ��. ���� �ڷ��� ������ ��뿵��, �ڴ� �޸� ������ ���ϴ� ����.
		//23�����ο����� new BustTest��ŭ ���� Vehicle��ŭ�� ����ϰڴ� ����������
		//�װɷδ� �� �Ǵ� ����. �̹� ��� ��������ִ��߾���...
	}
}