package extends_test;	//����Ŭ����
public class temp2 extends temp{
	private boolean turbo;
	public temp2(boolean turbo, int speed) {
		super(speed);	    //�θ�Ŭ���� ������. extends�� class�� �����ڸ� �θ���(������Ų��) ���� �ȴ�.
		this.turbo = turbo; // �θ� �����ڿ��� speed�� �䱸�ϹǷ� ���� �־���� ��.
		System.out.println("�ڽĻ�����");
	}
	public temp2() {
		this(false,0);
	}
	public boolean isTurbo() {
		return turbo;
	}
	public void setTurbo(boolean turbo) {
		this.turbo = turbo;
	}
	@Override
	public String toString() {
		return "temp2 [turbo=" + turbo + "]";
	}
	
}