package class_test;

public class MyMath extends Object {
	
	//public MyMath(){ }가 디폴트 생성자. 안만들면 그냥 숨겨져있다고 보면 됨.
	@Override
	public String toString() {
		//return super.toString();
		return "여기는 toString Override입니다.";
		
	}													//오버라이드 = 부모와 똑같아야 함, 오버로딩은 수백번해도 됨(매개변수 개수와 타입만)
	public int square(int i) {
		return i*i;
	}
	public double square(double i) {
		return i*i;
	}
}