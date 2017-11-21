package examples;

public class BlockInitiation {
	int speed;
	{
		speed = 100;
		System.out.println("블럭초기화 PRINT");
	}
	public BlockInitiation() {
		speed=10;
		System.out.println("생성자에서 PRINT : "+speed);
	}

}
