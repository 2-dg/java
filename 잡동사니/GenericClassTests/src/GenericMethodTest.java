public class GenericMethodTest {

	public static void main(String[] args) {
		Integer[] iArray = {10,20,30,40,50};
		Double[] dArray = {1.1,2.2,3.3,4.4,5.5};
		Character[] cArray = {'a','b','c','d','e'};
		Integer[] iiArray = new Integer[] {new Integer(10),20,30};
		String[] sArray = new String[] {new String("�ƾ�"),"�ƾƾ�","�ƾƾƾ�","��", "������"};
		
		printArray(iArray);
		printArray(dArray);
		printArray(cArray);
		printArray(sArray);
		System.out.println();
		printArray2(iArray);
		printArray2(dArray);
		printArray2(cArray);
		printArray2(sArray);
	}

	public static void printArray(Object[] array) {
		for(Object data : array) {
			System.out.print(data+"\t");		//������ data�տ� ĳ������ ����� ��.
		}
		System.out.println();
	}
	public static<T> void printArray2(T[] array) {
		for(T data : array) {
			System.out.print(data+"\t");
		}
		System.out.println();
	}
}
