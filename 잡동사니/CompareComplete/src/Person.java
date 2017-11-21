/*Person�̶�� Ŭ������ �����϶�. Person�� �̸�(name)�� Ű(height)�� �ʵ�� ������. 
Person�� ������ ������ Comparable �������̽��� �����Ѵ�. 
�� Comparable �������̽��� �̿��Ͽ��� ���� Ű ū ����� �̸���
��ȯ�ϴ� �޼ҵ� getMaximum(Person[] array)�� ������� �׽�Ʈ�϶�.
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
			System.out.println("�κ���ü Object o���� �ڽĿ��� Person�� �����.");
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







