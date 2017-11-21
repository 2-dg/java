package set_examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetMain {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		for(int i=0;i<100000;i++) {
			a.add("수지"+i);
		}
		ArrayList<String> b = new ArrayList<String>();
		for(int i=0;i<100000;i++) {
			b.add("수지"+i);
		}
		Set<String> c = new HashSet<String>();
		//HashSet하면 String의 해쉬코드값을 갖고 있으므로 같은 String은 저장되지 않는 것이다.
		c.add("아");
		c.add("아");
		c.add("아아");
		c.add("아아아");
		c.add("아아아아");
		c.add("아아아아아");
		Set<Box> d = new HashSet<Box>();
		d.add(new Box(1,"홍길동"));
		d.add(new Box(2,"김길동"));
		d.add(new Box(3,"최길동"));
		d.add(new Box(4,"이길동"));
		d.add(new Box(5,"박길동"));
		d.add(new Box(1,"홍길동"));					//이퀄스와 해쉬코드 구현을 안하면 중복값이 들어감
		for(Box aa : d) {
			System.out.println(aa);
		}
		Set<String> aa = new HashSet<String>();
		Set<String> bb = new HashSet<String>();
		aa.add("aa");
		aa.add("bb");
		aa.add("cc");
		bb.add("aa");
		bb.add("bb");
		bb.add("cc");
		bb.add("dd");
		bb.add("ee");
		aa.addAll(bb);
		Iterator<String> sss = aa.iterator();
		while(sss.hasNext()) {
			String pri = sss.next();
			System.out.println(pri);
		}
		
		
//		a.add("수지");
//		a.add("아이유");
//		a.add("나라");
//		a.add("김세정");
//		a.add("유담");
//
//		for(String val : a) {
//			System.out.println(val);
//		}
//		System.out.println();
//		a.set(1, "유지");
//		a.add(2,"혜민");
//		a.remove("유담");
//		
//		for(String val : a) {
//			System.out.println(val);
//		}
//		System.out.println();
//		for(int i=0;i<a.size();i++) {
//			System.out.println(a.get(i));
//		}
		/*a.clear();
		System.out.println("\n클리어 후");
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i));
		}*/
		long start = System.nanoTime();
		Iterator iter = a.iterator();			//형변환은 속도를 많이 잡아먹으므로 제네릭으로 설정해주는 게 좋다
		while(iter.hasNext()) {
			String str = (String)iter.next();					//만약에 제네릭설정을 안하면 (String)iter.next();식으로 해야 함
		}
		long end = System.nanoTime();
		System.out.println("강제 형변환의 경과시간 : "+(end-start));

		long start1 = System.nanoTime();
		Iterator <String>iter1 = b.iterator();			//형변환은 속도를 많이 잡아먹으므로 제네릭으로 설정해주는 게 좋다
		while(iter1.hasNext()) {
			String str = iter1.next();					//만약에 제네릭설정을 안하면 (String)iter.next();식으로 해야 함
		}
		long end1 = System.nanoTime();
		System.out.println("제네릭의 경과시간 : "+(end1-start1));
		for(String val : c) {
			System.out.println(val);
		}
		
		
	}
}