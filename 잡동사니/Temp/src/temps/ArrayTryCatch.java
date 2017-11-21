package temps;

public class ArrayTryCatch {

	public static void main(String[] args) {
		int[] array = new int[] { 10, 20, 30, 40, 50 };
		for (int i = 0; i <= array.length; i++) {
			try {
			System.out.println(i + "=" + array[i]);
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Àß Ã³¸®µÊ");
				e.printStackTrace();
			}
		}

	}

}
