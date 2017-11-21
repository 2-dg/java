package bounded_type_parameter_compare;

public class BoxGeneric {
	private int num;
	public BoxGeneric(int num) {
		super();
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Box [num=" + num + "]";
	}
	public static <T extends Comparable> T getMax(T[] array) {
		T largest = array[0];
		for(int i=0;i<array.length;i++) {
			if(largest.compareTo(array[i])<0) {
				largest=array[i];
			}
		}
		return largest;
	}	
}