package polymorphism;

public class VehicleExample {
	public static void main(String[] args) {
		Vehicle vehicle = new Bus();
		vehicle.run();
		//vehicle.checkFare();
		//위 메소드는 실행이 안 된다.
		//자 설명을 해보자면 맨 앞의 Vehicle이라는 선언은
		//뒤의 변수에 Vehicle모양의 메모리를 찍겠다는 건데
		//Bus Class는 어찌 보면 Vehicle인터페이스의 클래스라
		//Vehicle을 포함한 데다가 자신의 범위가 따로 있는 모습이다.
		//하지만 vehicle은 Vehicle로 찍힌 메모리모양이므로 Bus Class만 갖고 있는
		//CheckFare메소드를 인식할 수 없다.
		//이때는 우리가 실수를 정수로 바꾸거나 할 때 쓰는 형변환을 하면 된다.
		Bus bus = (Bus) vehicle;
		//결국 Bus만큼의 메모리를 확보해주고 그 안에다가
		//Bus로 형변환한 vehicle을 넣어주면 된다.
		//아니 이러면 사실 메모리 용량은 vehicle만큼만 확보되더라도
		//Bus만큼의 메소드는 구현이 돼있는건가??
		bus.checkFare();

		Vehicle vehicle2 = new BusTest("테스트시다");
		vehicle2.run();
		//vehicle2.checkFare();
		//안되자나
		BusTest busTest = (BusTest) vehicle2;
		busTest.checkFare();
		//체크해보니까 돼있네
		//아 기억나슴. 앞의 자료형 선언은 사용영역, 뒤는 메모리 영역을 말하는 거임.
		//23번라인에서는 new BustTest만큼 만들어서 Vehicle만큼만 사용하겠다 선언했으니
		//그걸로는 안 되는 거임. 이미 사실 만들어져있다했었지...
	}
}