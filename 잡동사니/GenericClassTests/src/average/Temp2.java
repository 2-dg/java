package average;

public class Temp2 {

	public static void main(String[] args) {
		Integer[] iv = new Integer[] { 10, 20, 30, 40 };
		Double[] dv = new Double[] { 11.1, 22.2, 33.3, 44.4 };
		MyMath2<Integer> mm = new MyMath2(iv);
		MyMath2<Double> md = new MyMath2(dv);
		System.out.println(mm.getAverage());
		System.out.println(md.getAverage());
	}
}