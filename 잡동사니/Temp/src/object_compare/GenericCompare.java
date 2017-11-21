package object_compare;
public class GenericCompare<T extends GenericCompare> implements Comparable<T> {
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
	public int compareTo(T o) {
		if(this.height-(o).getHeight()>0){
			return 1;
		}else if(this.height - (o).getHeight()<0) {
			return -1;
		}else return 0;
	}
	public static void main(String[]args) {
		GenericCompare p = new GenericCompare("아아아",180);
		GenericCompare p1 = new GenericCompare("아아아",179);
		System.out.println(p.compareTo(p1));
		
	}
}