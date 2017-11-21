package examples;

public class OuterClass {
	private int value = 10;
	
	public OuterClass(int value) {
		this.value=value;
		InnerClass in = new InnerClass(value);
		in.myMethod();
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int value) {
		InnerClass in = new InnerClass(value);
		this.value=in.getIa();
	}
	@Override
	public String toString() {
		return "value = " +value;
	}
	class InnerClass {
		public int ia=11;
		public InnerClass(int ia) {
			this.ia=ia;
		}
		public int getIa() {
			return this.ia;
		}
		public void setIa(int ia) {
			this.ia=ia;
		}
		@Override
		public String toString() {
			return "InnerClass [ia=" + ia + "]";
		}
		public void myMethod() {
			System.out.println("OuterClass's private int vaule = "+value);
		}
	}
}