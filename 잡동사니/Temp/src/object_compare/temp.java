package object_compare;

import java.util.Scanner;

public class temp{
	public static void main(String[] args) {
		Person[] p = new Person[5];
		p[0]=new Person("����", 179);
		p[1]=new Person("����", 178);
		p[2]=new Person("��ȭ", 180);
		p[3]=new Person("����", 189);
		p[4]=new Person("�¼�", 188);
		Person maxPerson = getMAximum(p);
		System.out.println(maxPerson);
	}
	private static Person getMAximum(Person[] p) {
		Person max = p[0];		
/*		int[] array = new int[] {5,6,4,2,1};
		int maa = array[0];
		for(int i=0;i<array.length;i++) {
			if(maa < array[i]) {
				maa=array[i];
			}
		}
		System.out.println("���� ū �� "+maa);*/
		for(int i=0;i<p.length;i++) {
			if(max.compareTo(p[i])<0) {
				max=p[i];
			}
		}		
		return max;
	}
}