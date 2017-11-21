
public class CompareMain {

	public static void main(String[] args) {
		PersonObject[] p = new PersonObject[3];
		PersonObject max = new PersonObject();
		p[0] = new PersonObject(170,"A");
		p[1] = new PersonObject(175,"B");
		p[2] = new PersonObject(180,"C");
		
		
		max = getMax(p);
		System.out.println(max);
		PersonObject max2 = new PersonObject();
		
		PersonGeneric[] pp = new PersonGeneric[3];
		PersonGeneric maxx = new PersonGeneric();
		pp[0] = new PersonGeneric(170,"A");
		pp[1] = new PersonGeneric(175,"B");
		pp[2] = new PersonGeneric(180,"C");
		maxx = getMax2(pp);
		System.out.println(maxx);

	}

	private static PersonGeneric getMax2(PersonGeneric[] pp) {
		PersonGeneric max = pp[0];
		for(int i=0;i<pp.length;i++) {
			if(max.compareTo(pp[i])<0) {
				max=pp[i];
			}
		}
		return max;
	}

	private static PersonObject getMax(PersonObject[] o) {
		PersonObject max = o[0];
		for(int i=0;i<o.length;i++) {
			if(max.compareTo(o[i])<0) {
				max=o[i];
			}
		}
		return max;
	}

}
