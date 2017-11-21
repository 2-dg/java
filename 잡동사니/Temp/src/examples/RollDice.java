package examples;

public class RollDice {
	public static final int SIZE = 6;  		  //static을 빼면 안에서 못씀. 인스턴스가 되기 때문.
	public static final int COUNT = 10_000;	  //단위구별용으로 언더바 사용가능
	public final int SIZE2 = 6;				  //이렇게 인스턴스 멤버변수로 만들어놓으면 객체생성으로 참고할 수는 있음
	public static void main(String[]args) {
		int index=0;
		int[] freq = new int[RollDice.SIZE];  //그냥 SIZE를 써도 되지만 클래스명으로 접근가능하기 때문에 가능
		//final int SIZE = 7; 				  //전역VS지역 중복되면 지역을 씀(더 가까운). main안에선 SIZE=7 		
											  //static을 빼면 안에서 못씀. 인스턴스가 되기 때문.
											  //하지만 자기 안이기 때문에 생략이 가능한 것이고 다른 클래스에서 쓰려면
											  //저렇게 써줘야 함.	
		RollDice roll = new RollDice();
		int a =10*roll.SIZE2;				  //이렇게 하면  a=10*6이 됨.
		
		for(int i=0;i<COUNT;i++) {
			index = (int)(Math.random()*6);
			freq[index]++;
		}		
		print_array(freq);
	}
	public static void print_array(int[] freq) {
		//new int[SIZE];                      // 여기에서의 SIZE는 4행의 6임.
		for(int i=0;i< freq.length; i++) {
			System.out.println((i + 1) + "눈금의 횟수는 " + freq[i]);
	}
	}
}
