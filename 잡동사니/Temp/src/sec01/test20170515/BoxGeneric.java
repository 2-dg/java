package sec01.test20170515;

public class BoxGeneric <T> {
	private T data;
	public BoxGeneric(T data) {
		this.data=data;
	}
	public void print() {
		System.out.println(data);
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
