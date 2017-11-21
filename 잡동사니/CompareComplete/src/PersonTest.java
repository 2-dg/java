//메소드 getMaximum(Person[] array)을 구현라고 테스트하라.
public class PersonTest {

	public static void main(String[] args) {
		Person[] p = new Person[5];
		p[0] = new Person("수현", 179);
		p[1] = new Person("성빈", 178);
		p[2] = new Person("승화", 180);
		p[3] = new Person("태현", 189);
		p[4] = new Person("태수", 188);
		
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
		System.out.println("가장키큰값은 " +max.getHeight() + "성명은 " + max.getName() );
		
		return max;
	}

}


