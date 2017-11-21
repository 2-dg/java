package singletone_test;

public class temp {
	private static temp t = null;
	private temp() {	//생성자가 private이므로 보통방법으로 생성이 불가
	}					//그래서 정적으로 선언한 함수에서 자체 클래스를 생성시키고
						//null이 아닐 땐 더 생성 못하게 막는 것.
	public static temp getInstance() {
		if(t==null) {
			t= new temp();    //t가 null상태이면 객체를 만들고 아니면 t를 리턴한다.
			System.out.println("temp객체 생성 완료");
		}					  //싱글톤방식(객체를 하나만 만드는방식)
		return t;
	}
}