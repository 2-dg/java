package class_test;

public class Television {
	public int channel; 					// Ŭ�������� ������ �� �� ����Ʈ���� ���� ��(0)
	private int volume;						// �̰͵��� �� �ν��Ͻ� ���������� �Ѵ�.
	private boolean onOff; 					// �����̺����� �ϸ� �ٸ����� ���� �Ұ����ϰ� �ϴ� ����.
	
	//����Լ�. ��ü�� ���� �� ��������� ���� ���ϴ� ������ �ٲٴ� ��.
	public Television(int channel, int volume, boolean onOff) {
		super();
		this.channel = channel;
		this.volume = volume;
		this.onOff = onOff;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channelIn) {
		this.channel = channelIn; 			// this�� Ŭ������ �������(4��)�� �ǹ�.
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volumeIn) {
		this.volume = volumeIn;
	}
	public boolean getOnoff() {
		return onOff;
	}
	public void setOnoff(boolean onOffIn) {
		this.onOff=onOffIn;
	}
	// �̰� ĸ��ȭ
}
