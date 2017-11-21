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
			sum += ((Number)list[i]).doubleValue();	//�ѹ�Ŭ������ �������Ѽ� ������ �����ϰ� �ϴ� ��.
		}
		return sum/(double)list.length;
	}
}