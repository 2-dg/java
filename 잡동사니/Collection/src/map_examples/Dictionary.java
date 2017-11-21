package map_examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Dictionary {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("map", "지도");
		map.put("java", "자바");
		map.put("school", "학교");
		Set<String> set = map.keySet();
		for(String str : set) {
			System.out.print(str+":");
			System.out.println(map.get(str));
		}
		System.out.println();
		Set<Entry<String,String>> entry = map.entrySet();
		for(Entry<String,String> str : entry) {
			System.out.print(str.getKey()+":");
			System.out.println(str.getValue());			//이건 밸류값도 가져오기 때문에 굳이 키값을 줘서 출력 안 해도 됨
		}
		System.out.println();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String str = it.next();
			String val = map.get(str);
			//System.out.println(map.get(it.next()));	로 하면 안 되는 이유는 next하면서 갖다 버리기 때문임.
			System.out.println(str + "="+val);
		}
		System.out.println();
		Iterator<Entry<String,String>> it1 = entry.iterator();
		while(it1.hasNext()) {
			System.out.println(it1.next());
		}
	}
}