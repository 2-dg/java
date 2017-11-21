package unbounded_wildcard;

import java.util.List;

public class MyList {
	public static void printList(List<?>list) {
		for(Object o : list) {
			System.out.print(o+" ");
		}
		System.out.println();
	}

}
