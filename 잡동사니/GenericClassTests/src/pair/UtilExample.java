package pair;

public class UtilExample {
	public static void main(String[]args) {
		Pair<String, Integer> pair = new Pair<>("홍길동",35);
		Integer age = Util.getValue(pair, "홍길동");
		System.out.println(age);
		
		ChildPair<String, Integer> cpair = new ChildPair<>("장길동",15);
		Integer cage = Util.getValue(cpair, "장길동");
		System.out.println(cage);
		
		
		
		
	}

}
