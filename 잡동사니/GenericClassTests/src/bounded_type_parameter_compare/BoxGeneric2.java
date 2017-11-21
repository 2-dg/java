package bounded_type_parameter_compare;

public class BoxGeneric2 implements Comparable {
	private int num;
	public BoxGeneric2(int num) {
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
	@Override
	public int compareTo(Object o) {
		BoxGeneric2 p = null;
		if(o instanceof BoxGeneric2) {
			p=(BoxGeneric2)o;
		}else {
			System.out.println("잘못된 객체 참조입니다.");
		}
		if(this.num>p.num) {
			return 1;
		}else if(this.num<p.num) {
			return -1;
		}else	return 0;
	}	
}