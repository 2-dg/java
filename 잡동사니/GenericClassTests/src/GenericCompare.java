public class GenericCompare<T> implements Comparable<T> {
	private String name;
	private int height;
	
	public GenericCompare(String name, int height) {
		super();
		this.name = name;
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public int compareTo(T o) {				//제네릭으로 작성시 밑의 if과정은 필요 없음
//		GenericCompare p = null;
//		if(o instanceof GenericCompare) {
//			p = (GenericCompare)o;
//		}
		if(this.height-((GenericCompare)o).getHeight()>0){
			return 1;
		}else if(this.height - ((GenericCompare)o).getHeight()<0) {
			return -1;
		}else return 0;
	}
	public static void main(String[]args) {
		GenericCompare p = new GenericCompare("아아아",180);
		GenericCompare p1 = new GenericCompare("아아아",179);
		System.out.println(p.compareTo(p1));
		
	}
}