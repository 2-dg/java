public class MyArrayAlg {
	public static void swap (Object[] oArray, int i, int j) {
		//System.out.println((String)oArray[i] + (String)oArray[j]);
		Object tmp = oArray[i];
		oArray[i] = oArray[j];
		oArray[j] = tmp;
		//System.out.println((String)oArray[i] + (String)oArray[j]);
	}
	public static<T> void swap2 (T[] tArray, int i, int j) {
		//System.out.println((String)oArray[i] + (String)oArray[j]);
		T tmp = tArray[i];
		tArray[i] = tArray[j];
		tArray[j] = tmp;
		//System.out.println((String)oArray[i] + (String)oArray[j]);
	}
	public static void main(String[]args) {
		String[] str = new String[] {"°¡","³ª","´Ù"};
		Integer[] intArray = new Integer[] {new Integer(10),20,30};
		
		for(String aa:str) {
			System.out.print(aa);
		}
		System.out.println();
		MyArrayAlg.swap(str, 0, 2);
		for(String aa:str) {
			System.out.print(aa);
		}
		System.out.println();
		for(int aa:intArray) {
			System.out.print(aa);
		}
		System.out.println();
		MyArrayAlg.swap(intArray, 0, 2);
		for(int aa:intArray) {
			System.out.print(aa);
		}
		System.out.println();
		System.out.println();
		for(String aa:str) {
			System.out.print(aa);
		}
		System.out.println();
		MyArrayAlg.swap2(str, 0, 2);
		for(String aa:str) {
			System.out.print(aa);
		}
		System.out.println();
		for(int aa:intArray) {
			System.out.print(aa);
		}
		System.out.println();
		MyArrayAlg.swap2(intArray, 0, 2);
		for(int aa:intArray) {
			System.out.print(aa);
		}
		
	}
}