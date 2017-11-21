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
		}			//���� for������ ������ �״�� �����ְ�
		System.out.println();
		while(!q.isEmpty()) {
			System.out.print(q.poll()+":");
			System.out.print(q.size()+"\n");
		}			//poll�� ������  �� ������ �ϳ��� �������
		
		
	}
}
