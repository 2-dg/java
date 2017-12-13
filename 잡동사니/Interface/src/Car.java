/*��, Tire�������̽��� ���������
 * Tire�� �����ϴ� Kumho�� Hankook�̶�� ����� ���������
 * �� ����� ���� �� ū ����� Car�� ����
 */
public class Car {
	//Tire�������̽������ �޸𸮿����� �����ϰ�
	//�� �ȿ� �� �������̽��� ������ �ѱ�Ÿ�̾� ����� �����Ͽ� ����
	Tire frontLeftTire = new HankookTire();
	Tire frontRightTire = new HankookTire();
	Tire rearLeftTire = new HankookTire();
	Tire rearRightTire = new HankookTire();
	
	void run() {
		frontLeftTire.roll();
		frontRightTire.roll();
		rearLeftTire.roll();
		rearRightTire.roll();
	}
}
