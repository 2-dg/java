package p420;

public class Employee implements Employable{
	private String name;
	
	public Employee(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void print() {
		System.out.println("¿Ã∏ß¿∫ "+this.name);
	}
	
}
