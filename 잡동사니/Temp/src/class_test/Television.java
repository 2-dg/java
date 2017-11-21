package class_test;

public class Television {
	public int channel; 					// 클래스에서 힙으로 들어갈 때 디폴트값을 갖고 들어감(0)
	private int volume;						// 이것들을 다 인스턴스 멤버변수라고 한다.
	private boolean onOff; 					// 프라이빗으로 하면 다른데서 수정 불가능하게 하는 거임.
	
	//멤버함수. 객체를 만들 때 멤버변수를 내가 원하는 값으로 바꾸는 것.
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
		this.channel = channelIn; 			// this은 클래스의 멤버변수(4행)을 의미.
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
	// 이게 캡슐화
}
