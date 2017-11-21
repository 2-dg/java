package unbounded_wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyListTest {
	public static void main(String[] args) {
		List<Integer> li = Arrays.asList(1, 2, 3);
		List<String> ls = Arrays.asList("one", "two", "three");
		MyList.printList(li);
		MyList.printList(ls);
	}
}
