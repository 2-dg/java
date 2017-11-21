package synchronization;

public class Counter {
	private int value = 10;
	public synchronized void increment() {value++;}
	public synchronized void decrement() {value--;}
	public synchronized void printCounter() {System.out.println(value);}
	
	
}
