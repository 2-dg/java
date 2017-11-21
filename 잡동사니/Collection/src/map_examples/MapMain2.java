package map_examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MapMain2 {

	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap<String,Integer>();
		hm.put("������", 100);
		hm.put("�̹���", 200);
		hm.put("����ȭ", 300);
		//���� �ٷ� Iterator�� �� �� ����
		
		Set<String> key = hm.keySet();
		for(String val : key) {
			System.out.println(val);
			System.out.println(hm.get(val));
		}
		
		Iterator<String> it = key.iterator();
		while(it.hasNext()) {
			String keyy = it.next();
			Integer value = hm.get(keyy);
			System.out.println("Ű ���ͷ����� : "+keyy+" "+value);
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
			System.out.println("��Ʈ�� ���ͷ����� : "+key1+" "+value1);
			
		}
		
		
		
	}

}
