package examples;

public class BlockInitiation {
	int speed;
	{
		speed = 100;
		System.out.println("���ʱ�ȭ PRINT");
	}
	public BlockInitiation() {
		speed=10;
		System.out.println("�����ڿ��� PRINT : "+speed);
	}

}
