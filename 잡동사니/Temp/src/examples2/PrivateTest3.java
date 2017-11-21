package examples2;

import examples.PrivateTest;

public class PrivateTest3 {
	private int c = 0;
	PrivateTest test;
	
	public PrivateTest3() {
		c=20;
		test = new PrivateTest();
	}
}