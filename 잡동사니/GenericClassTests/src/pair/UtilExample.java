package pair;

public class UtilExample {
	public static void main(String[]args) {
		Pair<String, Integer> pair = new Pair<>("ȫ�浿",35);
		Integer age = Util.getValue(pair, "ȫ�浿");
		System.out.println(age);
		
		ChildPair<String, Integer> cpair = new ChildPair<>("��浿",15);
		Integer cage = Util.getValue(cpair, "��浿");
		System.out.println(cage);
		
		
		
		
	}

}
