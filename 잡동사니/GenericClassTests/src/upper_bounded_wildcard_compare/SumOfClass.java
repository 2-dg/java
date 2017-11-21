package upper_bounded_wildcard_compare;

import java.util.List;

public class SumOfClass {
	public static double sumOfList(List<? extends Number> list) {
		double s= 0.0;
		for(Number num : list) {
			s+=num.doubleValue();
		}
		return s;
	}
}
