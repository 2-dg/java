import java.io.Serializable;

public class ObjectStreamClass implements Serializable {
	private static final long serialVersionUID = 1L;	//해당된 클래스를 찾는 키
	private String name;
	private int no;
	public ObjectStreamClass(String name, int no) {
		super();
		this.name = name;
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "ObjectStreamClass [name=" + name + ", no=" + no + "]";
	}	
}