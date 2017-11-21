package sec01.test20170515;

public class Boxmain {

	private static final Object String = null;
	public static void main(String[] args) {
		BoxDouble bd = new BoxDouble(1.1);
		BoxFloat bf = new BoxFloat(2.0f);
		BoxInteger bi = new BoxInteger(3);
		System.out.println(bd.getNum());
		System.out.println(bf.getNum());
		System.out.println(bi.getNum());
		System.out.println();
		BoxObject bod = new BoxObject(new Double(300)); 
		BoxObject bof = new BoxObject(new Float(200.0f)); 
		BoxObject boi = new BoxObject(new Integer(100)); 
		System.out.println((double)bod.getA());
		System.out.println((float)bof.getA());
		System.out.println((int)boi.getA());
		System.out.println();
		print(bod);
		print(bof);
		print(boi);
		System.out.println();
		print(bd.getNum());
		print(bf.getNum());
		print(bi.getNum());
		BoxGeneric<String> boxgs = new BoxGeneric(new String("¾Æ¾Æ¾Æ"));
		boxgs.print();
		System.out.println(boxgs.getData());
	}
	public static<T> void print(T o){
		System.out.println(o);
	}
}