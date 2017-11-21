package map_examples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Dictionary {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("map", "����");
		map.put("java", "�ڹ�");
		map.put("school", "�б�");
		Set<String> set = map.keySet();
		for(String str : set) {
			System.out.print(str+":");
			System.out.println(map.get(str));
		}
		System.out.println();
		Set<Entry<String,String>> entry = map.entrySet();
		for(Entry<String,String> str : entry) {
			System.out.print(str.getKey()+":");
			System.out.println(str.getValue());			//�̰� ������� �������� ������ ���� Ű���� �༭ ��� �� �ص� ��
		}
		System.out.println();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String str = it.next();
			String val = map.get(str);
			//System.out.println(map.get(it.next()));	�� �ϸ� �� �Ǵ� ������ next�ϸ鼭 ���� ������ ������.
			System.out.println(str + "="+val);
		}
		System.out.println();
		Iterator<Entry<String,String>> it1 = entry.iterator();
		while(it1.hasNext()) {
			System.out.println(it1.next());
		}
	}
}