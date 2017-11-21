package examples;

public class PrivateTest2 {
	public int b=0;
	PrivateTest test=null;
	public void testB() {
		b=10;
		test = new PrivateTest();
		//PrivateTest.a = 20;
		test.printA();
	}
}