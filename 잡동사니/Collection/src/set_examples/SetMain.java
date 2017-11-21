package set_examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetMain {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		for(int i=0;i<100000;i++) {
			a.add("����"+i);
		}
		ArrayList<String> b = new ArrayList<String>();
		for(int i=0;i<100000;i++) {
			b.add("����"+i);
		}
		Set<String> c = new HashSet<String>();
		//HashSet�ϸ� String�� �ؽ��ڵ尪�� ���� �����Ƿ� ���� String�� ������� �ʴ� ���̴�.
		c.add("��");
		c.add("��");
		c.add("�ƾ�");
		c.add("�ƾƾ�");
		c.add("�ƾƾƾ�");
		c.add("�ƾƾƾƾ�");
		Set<Box> d = new HashSet<Box>();
		d.add(new Box(1,"ȫ�浿"));
		d.add(new Box(2,"��浿"));
		d.add(new Box(3,"�ֱ浿"));
		d.add(new Box(4,"�̱浿"));
		d.add(new Box(5,"�ڱ浿"));
		d.add(new Box(1,"ȫ�浿"));					//�������� �ؽ��ڵ� ������ ���ϸ� �ߺ����� ��
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
		
		
//		a.add("����");
//		a.add("������");
//		a.add("����");
//		a.add("�輼��");
//		a.add("����");
//
//		for(String val : a) {
//			System.out.println(val);
//		}
//		System.out.println();
//		a.set(1, "����");
//		a.add(2,"����");
//		a.remove("����");
//		
//		for(String val : a) {
//			System.out.println(val);
//		}
//		System.out.println();
//		for(int i=0;i<a.size();i++) {
//			System.out.println(a.get(i));
//		}
		/*a.clear();
		System.out.println("\nŬ���� ��");
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i));
		}*/
		long start = System.nanoTime();
		Iterator iter = a.iterator();			//����ȯ�� �ӵ��� ���� ��Ƹ����Ƿ� ���׸����� �������ִ� �� ����
		while(iter.hasNext()) {
			String str = (String)iter.next();					//���࿡ ���׸������� ���ϸ� (String)iter.next();������ �ؾ� ��
		}
		long end = System.nanoTime();
		System.out.println("���� ����ȯ�� ����ð� : "+(end-start));

		long start1 = System.nanoTime();
		Iterator <String>iter1 = b.iterator();			//����ȯ�� �ӵ��� ���� ��Ƹ����Ƿ� ���׸����� �������ִ� �� ����
		while(iter1.hasNext()) {
			String str = iter1.next();					//���࿡ ���׸������� ���ϸ� (String)iter.next();������ �ؾ� ��
		}
		long end1 = System.nanoTime();
		System.out.println("���׸��� ����ð� : "+(end1-start1));
		for(String val : c) {
			System.out.println(val);
		}
		
		
	}
}