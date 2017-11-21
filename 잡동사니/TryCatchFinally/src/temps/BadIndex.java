package temps;

public class BadIndex {
	public static final int SIZE = 10;

	public static void main(String[] args) {
		int[] array = new int[SIZE];
		for (int i = 0; i < 10; i++) {
			array[i] = i + 1;
			System.out.println(array[i]);
		}
		System.out.println("=====구분선=====");
		for (int a : array) {
			System.out.println(a);
		}
		try {
			System.out.println(array[9]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("유감입니다.");
		} finally {
			System.out.println("ㅎㅎㅎ");
		}

		try {
			System.out.println(array[12]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("유감입니다.");
			// e.printStackTrace(); //오류메세지 출력
			return;
		} catch (Exception e) {
			System.out.println("오류");
		} // Exception이 최상위 오류처리 클래스이므로
			// 마지막에 넣어야 함
		finally {
			System.out.println("ㅎㅎㅎ"); // finally는 return을 줘도 실행함
		}
		System.out.println("끝"); // 리턴이면 이건 실행 한함
	}
}