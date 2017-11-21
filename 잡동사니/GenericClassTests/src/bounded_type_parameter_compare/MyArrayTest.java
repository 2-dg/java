package bounded_type_parameter_compare;

public class MyArrayTest {

	public static void main(String[] args) {
		String[] list = new String[] {"abc","def","ghi"};
		Box[] box = new Box[] {new Box(10),new Box(40),new Box(30)};
		String max=MyArrayAlg.getMax(list);
		System.out.println(max);
		Box max2 = MyArrayAlg.getMax(box);
		System.out.println(max2);
		//list는 comparable도 상속받았기 때문에 실행되지만 Box는 Object가 전부이기 때문에 X
		
	}
}