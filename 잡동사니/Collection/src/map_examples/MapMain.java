package map_examples;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MapMain {

	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("������", new Integer(100));
		map.put("�̹���", 200);
		map.put("������", 100);
		map.put("������", 100);					
		//���� �����ִ� ����� �ϴ� Ű���� ������ �ٲٰ� ����ϰų� Entry<K,V>�ؼ� for each������ ����ϸ� �ȴ�.		
		//Ű���� ������� �־ for��,for-each�����ε� ����� �Ұ���
		//�׷��� �ؽ������� ��ȯ�ؼ� ����ؾ� ��.
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