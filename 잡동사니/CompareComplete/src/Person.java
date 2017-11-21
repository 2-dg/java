/*Person이라는 클래스를 정의하라. Person은 이름(name)과 키(height)를 필드로 가진다. 
Person은 본문에 나오는 Comparable 인터페이스를 구현한다. 
이 Comparable 인터페이스를 이용하여서 가장 키 큰 사람의 이름을
반환하는 메소드 getMaximum(Person[] array)을 구현라고 테스트하라.
*/
public class Person implements Comparable{
	private String name;
	private int height;

	public Person(String name, int height) {
		super();		this.name = name;		this.height = height;
	}
	
	public String getName(){
		return name;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	
	@Override
	public String toString() {
		return "Person [name=" + name + ", height=" + height + "]";
	}
	@Override
	public int compareTo(Object o) {
		Person p=null;
		if(o instanceof Person){
			p=(Person)o;
		}else{
			System.out.println("부보객체 Object o에는 자식영역 Person이 없어요.");
			return -2;
		}
		
		if(this.height - p.getHeight()==0){
			return 0;
		}else if(this.height - p.getHeight()>0){
			return 1;
		}else{
			return -1;
		}
	}
}







