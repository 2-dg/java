package map_examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MapMain2 {

	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap<String,Integer>();
		hm.put("조용필", 100);
		hm.put("이미자", 200);
		hm.put("하춘화", 300);
		//맵을 바로 Iterator로 쓸 순 없음
		
		Set<String> key = hm.keySet();
		for(String val : key) {
			System.out.println(val);
			System.out.println(hm.get(val));
		}
		
		Iterator<String> it = key.iterator();
		while(it.hasNext()) {
			String keyy = it.next();
			Integer value = hm.get(keyy);
			System.out.println("키 이터레이터 : "+keyy+" "+value);
		}
		
		Set<Entry<String, Integer>> es = hm.entrySet();
		for(Entry<String, Integer> val : es) {
			System.out.println(val);
			System.out.println(val.getKey());
			System.out.println(val.getValue());
		}
		Iterator<Entry<String,Integer>> it1 = es.iterator();
		while(it1.hasNext()) {
			Entry<String,Integer> ent1 = it1.next();
			String key1 = ent1.getKey();
			Integer value1 = ent1.getValue();
			System.out.println("엔트리 이터레이터 : "+key1+" "+value1);
			
		}
		
		
		
	}

}
