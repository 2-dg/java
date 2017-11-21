package average;

public class MyMath2 <T>{
	private T[] list;
	public MyMath2(T[] list) {
		super();
		this.list = list;
	}
	public Double getAverage() {
		double sum = 0.0;
		for(int i = 0;i<list.length;i++) {
			sum += ((Number)list[i]).doubleValue();	//넘버클래스로 한정시켜서 연산이 가능하게 하는 것.
		}
		return sum/(double)list.length;
	}
}