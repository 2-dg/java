package p420;

public interface Employable {
	public abstract String getName();
	default void print() {
		System.out.println("Employale�� Print�޼ҵ� ����");
	}
	static boolean isEmpty(String str) {
		if(str==null||str.trim().length()==0) {
			return true;
		}else {
			return false;
		}
		
	}
}
