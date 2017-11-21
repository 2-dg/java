package extends_test;

public class AnimalMain {

	public static void main(String[] args) {
		Eagle e = new Eagle(10, "있음", 2);
		Lion l = new Lion(100, "없음", 4);
		e.fly();
		e.eat();
		e.sleep();
		l.roar();
		l.eat();
		e.sleep();
		System.out.println(e);
		System.out.println(l);
	}
}
