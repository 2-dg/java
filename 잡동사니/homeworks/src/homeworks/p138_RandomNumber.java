package homeworks;
public class p138_RandomNumber {
	public static void main(String[]args){
		double value = 0.0;
		int num=0;
		value = Math.random();					//클래스로 접근할 수 있는 건 무조건 정적멤버함수(Math->대문자)
		System.out.println("랜덤값 = "+value);		//리턴값이 double이라 실수출력 0~0.999...
		num=(int)(value*=(100-50+1)+50);		//(최대값-최소값+1)+최소값 = 원하는 범위의 수
		System.out.println("랜덤값 = "+num);
	}
}