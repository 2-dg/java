package queue_test;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	public static void main(String[]args) {
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0;i<10;i++) {
			q.add(new Integer(i));
			//q.add(i);
		}
		for(Integer val:q) {
			System.out.print(val+"\t");
		}			//향상된 for문으로 뽑으면 그대로 남아있고
		System.out.println();
		while(!q.isEmpty()) {
			System.out.print(q.poll()+":");
			System.out.print(q.size()+"\n");
		}			//poll로 뽑으면  쓸 때마다 하나씩 사라진다
		
		
	}
}
