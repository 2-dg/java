package p406;

public class RectangleTest {

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(100, 30);
		Rectangle r2 = new Rectangle(200, 10);
		String str = "awdddddddd";
		int result = r1.compareTo(r2);
		switch (result) {
		case 0:
			System.out.println("두 개는 같은 넓이");
			break;
		case 1:
			System.out.println("r1이 큼");
			break;
		case -1:
			System.out.println("r2가 큼");
			break;
		case -10:
			break;
		default:
			break;
		}

		Comparable comp = new Comparable() {
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		// 람다
		Comparable comp2 = o -> 0;
	}
}