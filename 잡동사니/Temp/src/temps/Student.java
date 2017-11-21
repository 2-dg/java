package temps;

public class Student {
	private String name;
	private String address;
	private String phone_num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public Student(String name, String address, String phone_num) {
		super();
		this.name = name;
		this.address = address;
		this.phone_num = phone_num;
	}
	@Override
	public String toString() {
		return "이름 : " + name + ". 주소 : " + address + ". 연락처 : " + phone_num;
	}
	
}
