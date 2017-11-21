package map_examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MapMain3 {

	public static void main(String[] args) {
		HashMap<Box, Integer> map = new HashMap<Box,Integer>();	//LinkedMap���� ����� ������� ��
		map.put(new Box(1,"�̹���"),new Integer(100));
		map.put(new Box(2,"����ȭ"),new Integer(200));
		map.put(new Box(3,"��Ƽ��"),new Integer(300));
		map.put(new Box(4,"��â��"),new Integer(400));
		map.put(new Box(4,"��â��"),new Integer(500));
		Set<Box> key = map.keySet();
		for(Box box:key) {
			Integer score = map.get(box);
			System.out.println(box+"="+score);
		}
		Set<Entry<Box,Integer>> entry = map.entrySet();
		for(Entry<Box,Integer> e : entry) {
			Box box = e.getKey();
			Integer num = e.getValue();
			System.out.println();
			System.out.print(e.getKey());
			System.out.println("="+e.getValue());
			System.out.println(box+"="+num);
		}
		System.out.println();
		Iterator<Box> iter=key.iterator();
		while(iter.hasNext()) {
			Box box = iter.next();
			Integer num = map.get(box);
			System.out.println(box + " " + num);
		}	
	}
}