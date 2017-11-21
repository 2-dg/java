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
//			if(max<array[i]) {						//그냥 이렇게 하면 객체끼리 비교하는 것이기 때문에 안 됨.
//													//원하는 값을 얻으려면 Comparable을 구현한 객체끼리
//													//함수를 만들어서 그 내에서 비교해야 한다.
//				max=array[i];
//			}
//		}
//		System.out.println(max);	
//		return null;
	}
}