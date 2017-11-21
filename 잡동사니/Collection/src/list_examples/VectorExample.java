package list_examples;

import java.util.List;
import java.util.Vector;

public class VectorExample {

	public static void main(String[] args) {
		List<Board> list = new Vector<Board>();
		list.add(new Board("力格1", "郴侩1", "历磊1"));
		list.add(new Board("力格2", "郴侩2", "历磊2"));
		list.add(new Board("力格3", "郴侩3", "历磊3"));
		list.add(new Board("力格4", "郴侩4", "历磊4"));
		list.add(new Board("力格5", "郴侩5", "历磊5"));
		list.remove(2);
		list.remove(3);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
			Board board = list.get(i);
			System.out.println(board.subject + "\t" + board.content + "\t" + board.writer);
		}
		for(Board board : list) {
			System.out.println(board);
		}
		
	}

}
