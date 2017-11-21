package extends_test;	//서브클래스
public class temp2 extends temp{
	private boolean turbo;
	public temp2(boolean turbo, int speed) {
		super(speed);	    //부모클래스 생성자. extends한 class의 생성자를 부른다(생성시킨다) 보면 된다.
		this.turbo = turbo; // 부모 생성자에서 speed를 요구하므로 같이 넣어줘야 함.
		System.out.println("자식생성자");
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