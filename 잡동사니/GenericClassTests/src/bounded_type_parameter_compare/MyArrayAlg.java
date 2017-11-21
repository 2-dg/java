package bounded_type_parameter_compare;

public class MyArrayAlg {
	public static<T extends Comparable> T getMax(T[] a) {
		if(a==null||a.length==0) {
			return null;
		}
		T largest = a[0];
		for(int i=0;i<a.length;i++) {
			if(largest.compareTo(a[i]) <0) {
			largest = a[i];	
			}
		}
		return largest;
//		Object[] array = new Object[] {new Integer(10),20,30,40,50};
//		Object max=array[0];
//		for(int i=0;i<array.length;i++) {
//			if(max<array[i]) {						//�׳� �̷��� �ϸ� ��ü���� ���ϴ� ���̱� ������ �� ��.
//													//���ϴ� ���� �������� Comparable�� ������ ��ü����
//													//�Լ��� ���� �� ������ ���ؾ� �Ѵ�.
//				max=array[i];
//			}
//		}
//		System.out.println(max);	
//		return null;
	}
}