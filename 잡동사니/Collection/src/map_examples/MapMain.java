package map_examples;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MapMain {

	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("조용필", new Integer(100));
		map.put("이미자", 200);
		map.put("조영남", 100);
		map.put("조용필", 100);					
		//맵을 보여주는 방법은 일단 키값을 셋으로 바꾸고 출력하거나 Entry<K,V>해서 for each문으로 출력하면 된다.		
		//키값과 밸류값이 있어서 for문,for-each문으로도 출력이 불가능
		//그래서 해쉬셋으로 전환해서 사용해야 함.
//		Set<String> key =map.keySet();
//		for(String str : key) {
//			System.out.println(str);
//			System.out.println(map.get(str));
//		}
		Set<Entry<String, Integer>> set =map.entrySet();
		for(Entry<String, Integer> ent:set) {
			System.out.println(ent.getKey());
			System.out.println(ent.getValue());
			System.out.println(ent);
		}
		
	}
}