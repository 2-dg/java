//�޼ҵ� getMaximum(Person[] array)�� ������� �׽�Ʈ�϶�.
public class PersonTest {

	public static void main(String[] args) {
		Person[] p = new Person[5];
		p[0] = new Person("����", 179);
		p[1] = new Person("����", 178);
		p[2] = new Person("��ȭ", 180);
		p[3] = new Person("����", 189);
		p[4] = new Person("�¼�", 188);
		
		Person maxPerson = getMaximum(p);
		System.out.println(maxPerson);

	}

	private static Person getMaximum(Person[] p) {
		Person max = p[0];
		
//		int[] array = new int[]{5,6,7,2,3};
//		int max = array[0];
		for(int i=0; i<p.length; i++    ){
			if(max.compareTo(p[i])<0 ){
				max = p[i];
			}
		}
		System.out.println("����Űū���� " +max.getHeight() + "������ " + max.getName() );
		
		return max;
	}

}


