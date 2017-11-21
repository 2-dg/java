package object_compare;

import java.util.Scanner;

public class temp{
	public static void main(String[] args) {
		Person[] p = new Person[5];
		p[0]=new Person("수현", 179);
		p[1]=new Person("성빈", 178);
		p[2]=new Person("승화", 180);
		p[3]=new Person("태현", 189);
		p[4]=new Person("태수", 188);
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
		System.out.println("가장 큰 값 "+maa);*/
		for(int i=0;i<p.length;i++) {
			if(max.compareTo(p[i])<0) {
				max=p[i];
			}
		}		
		return max;
	}
}