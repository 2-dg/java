package set_examples;

import java.util.Objects;

public class Box implements Comparable {
	private int number;
	private String name;
	public Box(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Box [number=" + number + ", name=" + name + "]";
	}
	@Override
	public boolean equals(Object obj) {				//�̰� ���߿� �ϰ� ��.
		Box box=null;
		if(obj instanceof Box) {
			box = (Box)obj;
		}else {
			return false;
		}
		return this.number == box.getNumber() && name.equals(box.getName());
	}			//�� ������ ���ؼ� �⺻Ű�� ���ϴ� ���̴�. ���� �������� ���ָ� Ű���� number�� �Ǿ� number�� ������ ����.
				//��� ���� �������� ���ָ� ���� �ؽ��ڵ尪�� ���ؾ� �ϹǷ� �ؽ��ڵ��� name�� ���־� �Ѵ�.
				//�׷��ϱ� �׳� String�̳� int ���� �⺻ �ڷ������� ������ �ÿ��� ���� Ŭ������ ���� ������
				//����ǹǷ� ���� �ؽ����� ���ؼ� ���������� ����ڰ� ���� ��ü�� Set�� �����ų ��쿣
				//���� ��ü���� ���� �ؽ����� �ٸ��� ������ �� ����. �װ� ���� ���ؼ� ����ڰ� �������� �ؽ��ڵ带 �����ϴ� ���̴�.
	@Override
	public int hashCode() {
		return Objects.hash(name,number);		//�ؽ��ڵ带 �����ؼ� �־��ִ� ��. �̰� ���� �ϰ�
		
	}
	
	@Override
	public int compareTo(Object o) {
		Box box=null;
		if(o instanceof Box) {
			box = (Box)o;
		}else {
			System.out.println("�߸��� ��ü�����Դϴ�.");
			return 0;
		}
		if(this.number == box.getNumber()) {
			return 1;
		}else return -1;
	}
}